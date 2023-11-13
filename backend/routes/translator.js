const { exec, execFile } = require("child_process");
const path = require("path");
var express = require("express");
const fs = require("fs");
var router = express.Router();

/* GET users listing. */
router.post("/", function (req, res, next) {
  //Save txt in tmp folder
  const txt = req.files.file;
  console.log(txt.name);
  try {
    fs.writeFile("routes/tmp/input.txt", txt.data, (err) => {
      if (err) {
        console.error("Error writing to file:", err);
        return res.status(500).send("Internal Server Error");
      }
      
      const javaPath = path.resolve(__dirname)+"\\javaw";
      const executablePath = "java pyTranslator";
      const inputFilePath = path.resolve(__dirname)+"\\tmp\\input.txt";
      
      console.log(javaPath);
      // Execute the C++ executable

      
        const child = exec(`cd ${javaPath} & ${executablePath} ${inputFilePath}`, (error, stdout, stderr) => {
          if (error) {
            console.error("Error:", error);
            return res.status(500).send("Internal Server Error");
          }
          console.log("stdout:", stdout);
     
        });
    });
  } catch (err) {
    console.error("Error:", err);
    return res.status(500).send("Internal Server Error");
  }

  const pythonFile = fs.readFileSync("routes/tmp/input.py", "utf8");
  res.send(pythonFile);
 
});

module.exports = router;
