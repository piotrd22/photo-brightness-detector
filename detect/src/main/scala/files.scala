import java.io.File
import java.nio.file.{Files,Paths,Path,StandardCopyOption}

object ToFiles {

    def getListOfPaths(dir: String): List[File] = {
      val directory = new File(dir)
      directory.listFiles
      .filter(_.isFile)
      .toList
  }

    def linMacOrWin(folderName: String) = {
      if (new File("src").getCanonicalPath.contains("\\")) {
        new File(s"src\\main\\scala\\${folderName}").getCanonicalPath
      } else {
        new File(s"src/main/scala/${folderName}").getCanonicalPath
      }
    }

    
    val which = () => if(new File("src").getCanonicalPath.contains("\\")) {"\\"} else {"/"}


    def moveAndRename(path: String, destination: String) = {
          Files.copy(Paths.get(path), 
          Paths.get(destination), 
          StandardCopyOption.REPLACE_EXISTING)
    }
        
}