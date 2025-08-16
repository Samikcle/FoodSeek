package main

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import controller.LayoutController
import javafx.fxml.FXMLLoader
import scalafx.scene.Scene
import scalafx.Includes.*
import scalafx.scene as sfxs
import javafx.scene as jfxs
import model.{Foodbank, Event}
import javafx.scene.image.Image

object MyApp extends JFXApp3:

  val foodbanks: List[Foodbank] = List(
    Foodbank(
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank1",
      address = "12 Jalan A",
      city = "KL",
      phone = "123456789",
      operatingHour = "Mon-Fri 9am-5pm",
      foodAvailable = "Rice \nBread \nMilk",
      additionalInformation = "Open to all, bring your own bag",
      owner = -1
    ),
    Foodbank(
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat 8am-6pm",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    ),
    Foodbank(
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat 8am-6pm",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    ),
    Foodbank(
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat 8am-6pm",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    ),
    Foodbank(
      logo = "placeholder.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat 8am-6pm",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 0
    ),
    Foodbank(
      logo = "FoodSeekLogo.png",
      backgroundImage = "placeholder.png",
      name = "Foodbank2",
      address = "1 Jalan wa",
      city = "Subang",
      phone = "123456789",
      operatingHour = "Mon-Sat 8am-6pm",
      foodAvailable = "Canned goods, Fresh produce",
      additionalInformation = "sdasd \ndsadasdasda \nsdasdasdasd \ndasda",
      owner = 1
    )
  )

  val events: List[Event] = List(
    Event(
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

  val citiesList = List("Subang", "KL", "RAA", "BB")

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

end MyApp
