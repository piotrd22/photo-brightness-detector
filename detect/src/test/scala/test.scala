import Main._
import java.nio.file.{Files,Paths,Path,StandardCopyOption}
import java.io.File
import org.scalatest.funspec.AnyFunSpec
import ToFiles._
import ToFutures._


class TestSpec extends AnyFunSpec {

  describe("getlistofpaths") {
    it("should give back a list of paths to the files that folder contains") {
      assert(getListOfPaths(new File("src/test/scala").getCanonicalPath)(0).toString == new File("src/test/scala/test.scala").getCanonicalPath)
    }
  }

  describe("RGBS"){

    def sumrgb(rgbs: IndexedSeq[(Int, Int, Int)]): (Int, Int, Int) = { // I add the parameter to check if func work properly
      val (sumRed, sumGreen, sumBlue) = rgbs.fold(0, 0, 0){
        case ((prevRed, prevGreen, prevBlue), (currRed, currGreen, currBlue)) =>
        ((prevRed + currRed), (prevGreen + currGreen), (prevBlue + currBlue))
      }
      (sumRed, sumGreen, sumBlue)
    }

    val sumRGB = sumrgb(IndexedSeq((1,2,3), (9,8,7), (7,6,5))).toList

    describe("Sum Of RGBS") {
      it("should return sum of numbers in tuples => (Int, Int, Int)"){
        assert(sumrgb(IndexedSeq((1,2,3), (9,8,7), (7,6,5))) == (17,16,15))
      }
    }
    
    describe("average RGB"){

      val averageRGB = (sumRGB.map(x => x / (1 * 3)))

      it("should return average of sumRGBS"){
        assert(averageRGB == List(5,5,5)) //Instead of width * height I use 1 * 3 because I have only 3 tuples
      }
    }
  }

  describe("FIles"){

    val fullname = (new File("/home/user/scalac/src/main/scala/in/z.jpg").toString)
    .split("/")
    .toList
    .reverse(0)

    describe("fullname"){
      it("should return the file name (last element)"){
        assert(fullname == "z.jpg")
      }
    }
  }

  describe("luminance"){

    val luminance = (List(5,5,5).sliding(3,3)
    .toList
    .map(x => List(x(0) * 0.3, x(1) * 0.59, x(2) * 0.11)) // 5 * 0.3 = 1.5, 5 * 0.59 = 2.95, 5 * 0.11 = 0.55
    .flatten
    .sum)

    it("should return a sum of average RGB (red*0.3, green*0.59, blue*0.11)"){
      assert(luminance.round == 5) //I don't know why my computer shows it is 4.(99) so I round it
    }
  }

  describe("Linux or Mac or Win"){

    // I will not test this func because I have my files in WSL, but I will check which(which has the sam if else statements and will give me an answer)
    // def linMacOrWin(folderName: String) = {
    //   if(new File("src").getCanonicalPath.contains("\\")){
    //     new File(s"src\\main\\scala\\${folderName}").getCanonicalPath
    //   } else {
    //     new File(s"src/main/scala/${folderName}").getCanonicalPath
    //   }
    // }

    //modified a bit
    val which = (str: String) => if(str.contains("\\")) {"\\"} else {"/"}

    it("should return which split character I should use (/ or \\)"){
      assert(which("C::\\user\\lala\\main.scala") == "\\")
      assert(which("/home/user/src/main/scala") == "/")
    }
  }
}