import Main._
import ToFiles._
import java.io.File
import javax.imageio.ImageIO
import java.awt.Color
import java.nio.file.{Files,Paths,Path,StandardCopyOption}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await}

object ToFutures {

    def deleteOutFiles(dir: String) = {
        val listOfPathOUT = getListOfPaths(dir)
        if (listOfPathOUT.length > 0) {
            for {
            i <- listOfPathOUT
            } yield Future { i.delete() }
        }
    }
    
    val toSplit = ToFiles.which()

    val cutOff = io.StdIn.readLine()

    val outDir = ToFiles.linMacOrWin("Out")
    deleteOutFiles(outDir)

    val dir = ToFiles.linMacOrWin("In")
    val listOfPaths =  ToFiles.getListOfPaths(dir)

    def aFutures(): List[Future[Path]] = {

        for (file <- listOfPaths) yield Future {

            val image = ImageIO.read(new File(file.toString))

            val fullname = (file.toString).split(toSplit)
            .toList
            .reverse(0)

            val name = fullname.split('.').toList(0)
            val end = fullname.split('.').toList(1)

            val width = image.getWidth
            val height = image.getHeight

            val rgbs = for {
                x <- 0 until width
                y <- 0 until height
                color = new Color(image.getRGB(x,y))
            } yield (color.getRed, color.getGreen, color.getBlue)

            def sumrgb(): (Int, Int, Int) = {
                val (sumRed, sumGreen, sumBlue) = rgbs.fold(0, 0, 0){
                case ((prevRed, prevGreen, prevBlue), (currRed, currGreen, currBlue)) =>
                ((prevRed + currRed), (prevGreen + currGreen), (prevBlue + currBlue))
                }
                (sumRed, sumGreen, sumBlue)
            }

            val sumRGB = sumrgb().toList

            val averageRGB = (sumRGB.map(x => x / (width * height)))

            val luminance = (averageRGB.sliding(3,3)
            .toList
            .map(x => List(x(0) * 0.3, x(1) * 0.59, x(2) * 0.11))
            .flatten
            .sum)
            
            val luminance0_100 = ((luminance / 255) * 100).round

            val luminanceReverse = 100 - luminance0_100

            def brightOrDark(): String = {
                if (cutOff.length == 0) {
                if (luminanceReverse < 80) "bright"
                else "dark"
                } 
                else {
                if (luminanceReverse < cutOff.toInt) "bright"
                else "dark"
                }
            }

            val answer = brightOrDark()

            ToFiles.moveAndRename(s"${dir}/${fullname}", s"${outDir}/${name}_${luminanceReverse}_${answer}.${end}")
        
        }

    }

}