import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import controller.FoodbanksController
import javafx.fxml.FXMLLoader
import scalafx.scene.Scene
import scalafx.Includes.*
import scalafx.scene as sfxs
import javafx.scene as jfxs
import model.Foodbank
import scalafx.scene.layout.AnchorPane

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
      claimed = false
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
      claimed = true
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
      claimed = true
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
      claimed = true
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
      claimed = true
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
      claimed = true
    )
  )

  override def start(): Unit =

    val loader = new FXMLLoader(getClass.getResource("/view/Foodbanks.fxml"))
    val root = loader.load[javafx.scene.Parent]()
    val controller = loader.getController[FoodbanksController]
    controller.setFoodbanks(foodbanks)

    stage = new PrimaryStage {
      title = "Foodbanks"
      scene = new Scene(root)
    }

end MyApp
