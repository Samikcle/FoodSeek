package main

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import controller.LayoutController
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

  val events: List[Event] = List(
    Event(
      id = 0,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Community Food Fair",
      address = "123 Main Street, Downtown",
      city = "KL",
      date = "20/03/2026",
      time = "10:00 AM - 2:00 PM",
      foodAvailable = "Rice, Vegetables, Canned Goods",
      additionalInformation = "Open to all, bring your own bags."
    ),
    Event(
      id = 0,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Weekend Food Drive",
      address = "456 Market Road, Subang",
      city = "KL",
      date = "15/04/2025",
      time = "9:00 AM - 12:00 PM",
      foodAvailable = "Bread, Fruits, Dairy",
      additionalInformation = "Priority given to families with children."
    ),
    Event(
      id = 0,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Charity Meal Distribution",
      address = "789 Green Lane, Penang",
      city = "KL",
      date = "05/05/2026",
      time = "11:30 AM - 3:00 PM",
      foodAvailable = "Cooked Meals, Snacks, Drinks",
      additionalInformation = "Limited to first 200 people."
    ),
    Event(
      id = 0,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Neighborhood Food Sharing",
      address = "12 Garden Avenue, Johor Bahru",
      city = "Subang",
      date = "01/06/2026",
      time = "2:00 PM - 6:00 PM",
      foodAvailable = "Vegetables, Meat, Grains",
      additionalInformation = "Bring your own container for cooked food."
    )
  )

  val discounts: List[Discount] = List(
    Discount(
      id = 1,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      discount = "20% OFF on all buckets",
      address = "123 Main Street",
      city = "Subang",
      storeName = "KFC Downtown",
      expiryDate = "25/02/2025",
      additionalInformation = "Valid for dine-in and takeaway only."
    ),
    Discount(
      id = 1,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      discount = "Buy 1 Free 1 McFlurry",
      address = "45 Jalan Ampang",
      city = "KL",
      storeName = "McDonald's Ampang",
      expiryDate = "10/11/2025",
      additionalInformation = "Promo available after 6pm daily."
    ),
    Discount(
      id = 1,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      discount = "15% OFF Groceries above RM100",
      address = "Lot 88, Mid Valley Megamall",
      city = "KL",
      storeName = "Giant Hypermarket",
      expiryDate = "01/01/2026",
      additionalInformation = "Not applicable for electronics section."
    ),
    Discount(
      id = 1,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      discount = "RM10 OFF every RM50 spent",
      address = "No. 12, Jalan Bukit Bintang",
      city = "KL",
      storeName = "Tesco Bukit Bintang",
      expiryDate = "05/09/2025",
      additionalInformation = "Limited to first 500 customers daily."
    ),
    Discount(
      id = 1,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      discount = "30% OFF Large Pizzas",
      address = "77 Jalan Sultan",
      city = "Subang",
      storeName = "Pizza Hut Shah Alam",
      expiryDate = "31/12/2025",
      additionalInformation = "Valid for online orders only."
    )
  )

  val activities: ListBuffer[Activity] = ListBuffer(
    Activity(
      id = 0,
      userID = 1,
      name = "Alice Johnson",
      phone = "555-1234",
      ownerID = 1,
      activityName = "Food Pickup",
      address = "123 Main St",
      city = "Springfield",
      date = "31/12/2024",
      time = "10:00",
      notes = "Bring ID",
      status = "Pending"
    ),
    Activity(
      id = 1,
      userID = 2,
      name = "Bob Smith",
      phone = "555-5678",
      ownerID = 1,
      activityName = "Donation Drop",
      address = "456 Elm St",
      city = "Shelbyville",
      date = "31/12/2025",
      time = "14:30",
      notes = "Handle with care",
      status = "Approved"
    ),
    Activity(
      id = 2,
      userID = 1,
      name = "Carol Davis",
      phone = "555-9012",
      ownerID = 2,
      activityName = "Community Service",
      address = "789 Oak Ave",
      city = "Ogdenville",
      date = "31/12/2025",
      time = "09:15",
      notes = "Wear gloves",
      status = "Rejected"
    )
  )

  var citiesList: List[String] = List()

  var currentUserID = 0

  var ownedFoodbank: Foodbank = null

  val accounts: ListBuffer[Account] = ListBuffer(
    new Owner(0, "alice01", "pass123", "Alice Johnson", "555-1234", "Owner"),
    new User(1, "bob99", "secure456", "Bob Smith", "555-5678", "User"),
    new User(2, "charlieX", "charlie789", "Charlie Brown", "555-2468", "User"),
    new User(3, "dianaK", "hunter2024", "Diana King", "555-1357", "User"),
    new User(4, "eve007", "eveSecure!", "Eve Adams", "555-9999", "User")
  )


  override def start(): Unit =

    foodbanks = loadFoodbanksFromFile("src/main/resources/data/foodbanks.txt")
    saveFoodbanksToFile("src/main/resources/data/foodbanks.txt")
    citiesList = loadSupportedCities("src/main/resources/data/supportedcities.txt")

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
  }

  def updateActivity(updated: Activity): Unit = {
    activities(updated.id) = updated
  }

  def addActivity(newActivity: Activity): Unit = {
    activities.append(newActivity)
  }

  def setOwnedFoodbank(foodbank: Foodbank): Unit = {
    ownedFoodbank = foodbank
  }

  def newAccount(account: Account): Unit = {
    accounts.append(account)
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
        foodAvailable = parts(8),
        additionalInformation = parts(9),
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
        fb.foodAvailable,
        fb.additionalInformation,
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

end MyApp


