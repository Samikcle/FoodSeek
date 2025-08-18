package main

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import controller.LayoutController
import javafx.fxml.FXMLLoader
import scalafx.scene.Scene
import scalafx.Includes.*
import scalafx.scene as sfxs
import javafx.scene as jfxs
import model.{Foodbank, Event, Discount, Activity}
import javafx.scene.image.Image
import scala.collection.mutable.ListBuffer

object MyApp extends JFXApp3:

  val foodbanks: ListBuffer[Foodbank] = ListBuffer(
    Foodbank(
      id = 0,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank1",
      address = "12 Jalan A",
      city = "KL",
      phone = "123456789",
      operatingHour = "Mon-Fri, 9:00-17:00",
      foodAvailable = "Rice \nBread \nMilk",
      additionalInformation = "Open to all, bring your own bag",
      owner = -1
    ),
    Foodbank(
      id = 1,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat, 8:00-18:00",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    ),
    Foodbank(
      id = 2,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat, 8:00-18:00",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    ),
    Foodbank(
      id = 3,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat, 8:00-18:00",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    ),
    Foodbank(
      id = 0,
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat, 8:00-18:00",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 0
    ),
    Foodbank(
      id = 0,
      logo = "FoodSeekLogo.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat, 8:00-18:00",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    )
  )

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
      id = 1,
      userID = 1,
      name = "Alice Johnson",
      phone = "555-1234",
      ownerID = 4,
      activityName = "Food Pickup",
      address = "123 Main St",
      city = "Springfield",
      date = "31/12/2025",
      time = "10:00",
      notes = "Bring ID",
      status = "pending"
    ),
    Activity(
      id = 2,
      userID = 2,
      name = "Bob Smith",
      phone = "555-5678",
      ownerID = 3,
      activityName = "Donation Drop",
      address = "456 Elm St",
      city = "Shelbyville",
      date = "31/12/2025",
      time = "14:30",
      notes = "Handle with care",
      status = "approved"
    ),
    Activity(
      id = 3,
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
      status = "rejected"
    )
  )

  val citiesList = List("Subang", "KL", "RAA", "BB")

  val userID = 1

  override def start(): Unit =

    val loader = new FXMLLoader(getClass.getResource("/view/Layout.fxml"))
    val root = loader.load[javafx.scene.Parent]()
    val controller = loader.getController[LayoutController]
    //controller.disableAdminMenus()

    stage = new PrimaryStage {
      title = "FoodSeek"
      scene = new Scene(root)
    }
    stage.getIcons.add(new Image(getClass.getResourceAsStream("/images/FoodSeekLogo.png")))

  def updateFoodbank(updated: Foodbank): Unit = {
    foodbanks(updated.id) = updated
  }

end MyApp


