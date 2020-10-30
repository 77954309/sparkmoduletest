package com.lm.spark

import java.io.{BufferedOutputStream, File, FileOutputStream, OutputStream}

import scala.tools.nsc.GenericRunnerSettings
import scala.tools.nsc.interpreter.{ILoop, JPrintWriter, Results}
/**
  * @Classname SparkILoop
  * @Description TODO
  * @Date 2020/10/23 19:03
  * @Created by limeng
  */
object SparkILoopTest {
  def main(args: Array[String]): Unit = {
    val file = new File("out.txt")


    val out = new BufferedOutputStream(new FileOutputStream(file), 16)
    val sparkILoop = new ILoop(None,new JPrintWriter(out,true))

    val classpathJars =  System.getProperty("java.class.path").split(":").filter(_.endsWith(".jar"))
    println("classpathJars")
    classpathJars.foreach(println(_))

    val classpath = classpathJars.mkString(File.pathSeparator)
    println("classpath "+classpath)


    val settings = new GenericRunnerSettings(error(_))
    settings.usejavacp.value = true
    settings.processArguments(List("-classpath",classpath),true)
    settings.embeddedDefaults(Thread.currentThread().getContextClassLoader())
    sparkILoop.process(settings)

//    sparkILoop.beSilentDuring{
//      sparkILoop.processLine(":silent")
//
//
//    }

    val result: Results.Result = sparkILoop.interpret("print('test')")


    result match {
      case Results.Success =>
        out.flush()
        println("out :"+out.toString)
        out.close()
      case Results.Incomplete => println("incomplete code.")
        out.close()
      case Results.Error =>  println("Error out :"+out.toString)
        out.close()

    }



    sparkILoop.close()
  }
}

