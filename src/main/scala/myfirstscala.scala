package main

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import javafx.fxml.FXMLLoader
import scalafx.scene.Scene
import scalafx.Includes.*
import scalafx.scene as sfxs
import javafx.scene as jfxs
import javafx.scene.Parent
import model.{Account, Activity, Discount, Event, Foodbank, Owner, User}
import javafx.scene.image.Image
import scala.io.Source
import java.io.{File, PrintWriter}

import scala.collection.mutable.ListBuffer

object MyApp extends JFXApp3:

  var foodbanks: ListBuffer[Foodbank] = ListBuffer()

  var events: List[Event] = List()

  var discounts: List[Discount] = List()

  var activities: ListBuffer[Activity] = ListBuffer()

  var citiesList: List[String] = List()

  var currentUserID = 0

  var ownedFoodbank: Foodbank = null

  var accounts: ListBuffer[Account] = ListBuffer()

  override def start(): Unit =

    foodbanks = loadFoodbanksFromFile("src/main/resources/data/foodbanks.txt")
    citiesList = loadSupportedCities("src/main/resources/data/supportedcities.txt")
    events = loadEventsFromFile("src/main/resources/data/events.txt")
    discounts = loadDiscountsFromFile("src/main/resources/data/discounts.txt")
    activities = loadActivitiesFromFile("src/main/resources/data/activities.txt")
    accounts = loadAccountsFromFile("src/main/resources/data/accounts.txt")

    val loader = new FXMLLoader(getClass.getResource("/view/Login.fxml"))
    val root = loader.load[javafx.scene.Parent]()

    stage = new PrimaryStage {
      title = "FoodSeek"
      scene = new Scene(root)
      resizable = false
    }
    stage.getIcons.add(new Image(getClass.getResourceAsStream("/images/FoodSeekLogo.png")))


  def updateFoodbank(updated: Foodbank): Unit = {
    foodbanks(updated.id) = updated
    ownedFoodbank = updated
    saveFoodbanksToFile("src/main/resources/data/foodbanks.txt")
  }

  def updateActivity(updated: Activity): Unit = {
    activities(updated.id) = updated
    saveActivitiesToFile("src/main/resources/data/activities.txt")
  }

  def addActivity(newActivity: Activity): Unit = {
    activities.append(newActivity)
    saveActivitiesToFile("src/main/resources/data/activities.txt")
  }

  def setOwnedFoodbank(foodbank: Foodbank): Unit = {
    ownedFoodbank = foodbank
  }

  def newAccount(account: Account): Unit = {
    accounts.append(account)
    saveAccountsToFile("src/main/resources/data/accounts.txt")
  }

  def loadLogin(): Parent =
    val loader = new javafx.fxml.FXMLLoader(getClass.getResource("/view/Login.fxml"))
    loader.load[javafx.scene.Parent]()

  def setScene(root: Parent): Unit =
    stage.setScene(new Scene(root))

  def loadFoodbanksFromFile(filePath: String): ListBuffer[Foodbank] = {
    val source = Source.fromFile(filePath)
    val banks = source.getLines().toList.map { line =>
      val parts = line.split("\\|", -1).map(_.trim)
      Foodbank(
        id = parts(0).toInt,
        logo = parts(1),
        backgroundImage = parts(2),
        name = parts(3),
        address = parts(4),
        city = parts(5),
        phone = parts(6),
        operatingHour = parts(7),
        foodAvailable = parts(8).replace("\\n", "\n"),
        additionalInformation = parts(9).replace("\\n", "\n"),
        owner = parts(10).toInt
      )
    }
    source.close()
    ListBuffer.from(banks)
  }

  def saveFoodbanksToFile(filePath: String): Unit = {
    val writer = new PrintWriter(new File(filePath))
    foodbanks.foreach { fb =>
      val line = List(
        fb.id,
        fb.logo,
        fb.backgroundImage,
        fb.name,
        fb.address,
        fb.city,
        fb.phone,
        fb.operatingHour,
        fb.foodAvailable.replace("\n", "\\n"),
        fb.additionalInformation.replace("\n", "\\n"),
        fb.owner
      ).mkString("|")

      writer.println(line)
    }
    writer.close()
  }

  def loadSupportedCities(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)
    val line = source.getLines().toList.headOption.getOrElse("")
    source.close()
    line.split(",").map(_.trim).toList
  }

  def loadEventsFromFile(filePath: String): List[Event] = {
    val source = Source.fromFile(filePath)
    val evts = source.getLines().toList.map { line =>
      val parts = line.split("\\|", -1).map(_.trim)
      Event(
        id = parts(0).toInt,
        logo = parts(1),
        backgroundImage = parts(2),
        name = parts(3),
        address = parts(4),
        city = parts(5),
        date = parts(6),
        time = parts(7),
        foodAvailable = parts(8).replace("\\n", "\n"),
        additionalInformation = parts(9).replace("\\n", "\n")
      )
    }
    source.close()
    evts
  }

  def loadDiscountsFromFile(filePath: String): List[Discount] = {
    val source = Source.fromFile(filePath)
    val discs = source.getLines().toList.map { line =>
      val parts = line.split("\\|", -1).map(_.trim)
      Discount(
        id = parts(0).toInt,
        logo = parts(1),
        backgroundImage = parts(2),
        discount = parts(3),
        storeName = parts(4),
        address = parts(5),
        city = parts(6),
        expiryDate = parts(7),
        additionalInformation = parts(8).replace("\\n", "\n")
      )
    }
    source.close()
    discs
  }

  def loadActivitiesFromFile(filePath: String): ListBuffer[Activity] = {
    val source = Source.fromFile(filePath)
    val acts = source.getLines().toList.map { line =>
      val parts = line.split("\\|", -1).map(_.trim)
      Activity(
        id = parts(0).toInt,
        userID = parts(1).toInt,
        name = parts(2),
        phone = parts(3),
        ownerID = parts(4).toInt,
        activityName = parts(5),
        address = parts(6),
        city = parts(7),
        date = parts(8),
        time = parts(9),
        notes = parts(10).replace("\\n", "\n"),
        status = parts(11)
      )
    }
    source.close()
    ListBuffer.from(acts)
  }

  def saveActivitiesToFile(filePath: String): Unit = {
    val writer = new PrintWriter(new File(filePath))
    activities.foreach { act =>
      val line = List(
        act.id,
        act.userID,
        act.name,
        act.phone,
        act.ownerID,
        act.activityName,
        act.address,
        act.city,
        act.date,
        act.time,
        act.notes.replace("\n", "\\n"),
        act.status
      ).mkString("|")

      writer.println(line)
    }
    writer.close()
  }

  def loadAccountsFromFile(filePath: String): ListBuffer[Account] = {
    val source = Source.fromFile(filePath)
    val accs = source.getLines().toList.map { line =>
      val parts = line.split("\\|", -1).map(_.trim)
      val id = parts(0).toInt
      val username = parts(1)
      val password = parts(2)
      val name = parts(3)
      val phone = parts(4)
      val accType = parts(5)

      accType match {
        case "Owner" => new Owner(id, username, password, name, phone, accType)
        case "User" => new User(id, username, password, name, phone, accType)
        case other => throw new IllegalArgumentException(s"Unknown account type: $other")
      }
    }
    source.close()
    ListBuffer.from(accs)
  }

  def saveAccountsToFile(filePath: String): Unit = {
    val writer = new PrintWriter(new File(filePath))
    accounts.foreach { acc =>
      writer.println(acc.toDataLine)
    }
    writer.close()
  }

end MyApp


