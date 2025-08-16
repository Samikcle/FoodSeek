package controller

import main.MyApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.*
import javafx.scene.image.*
import javafx.scene.layout.VBox
import javafx.scene.Node
import javafx.collections.FXCollections
import scala.jdk.CollectionConverters.*
import scalafx.Includes._
import model.Discount
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DiscountController {
  @FXML
  private var DiscountLocationSelect: ComboBox[String] = _

  @FXML
  private var DiscountBackground: ImageView = _

  @FXML
  private var DiscountLogo: ImageView = _

  @FXML
  private var Discount: Label = _

  @FXML
  private var DiscountAddress: Label = _

  @FXML
  private var DiscountCity: Label = _

  @FXML
  private var StoreName: Label = _

  @FXML
  private var ExxpiryDate: Label = _

  @FXML
  private var FoodAvailable: Label = _

  @FXML
  private var DiscountContainer: VBox = _

  private var discounts: List[Discount] = List()
  private var cities: List[String] = List()

  private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  @FXML
  def initialize(): Unit = {
    setDiscounts(MyApp.discounts, MyApp.citiesList)
  }

  private def setDiscounts(list: List[Discount], citiesList: List[String]): Unit = {
    discounts = list
    cities = citiesList

    val today = LocalDate.now()
    discounts = list.filter(discount => {
      val expiry = LocalDate.parse(discount.expiryDate, dateFormat)
      expiry.isAfter(today)
    })

    setupCityComboBox()
    loadListItems(discounts)
    if (discounts.nonEmpty) {
      displayDiscount(discounts.head)
    }
  }

  private def setupCityComboBox(): Unit = {
    DiscountLocationSelect.setItems(FXCollections.observableArrayList(cities.asJava))

    DiscountLocationSelect.setOnAction(_ => {
      val selectedCity = Option(DiscountLocationSelect.getValue).getOrElse("")
      if (selectedCity.trim.isEmpty) {
        loadListItems(discounts)
      } else {
        val filtered = discounts.filter(_.city.equalsIgnoreCase(selectedCity.trim))
        loadListItems(filtered)
      }
    })
  }

  private def loadListItems(list: List[Discount]): Unit = {
    DiscountContainer.getChildren.clear()
    list.foreach { d =>
      val loader = new FXMLLoader(getClass.getResource("/view/ListItem.fxml"))
      val node: Node = loader.load()
      val controller = loader.getController[ListItemController]

      controller.setLogo(loadImage(d.logo))
      controller.setName(d.discount)
      controller.setCity(d.city)
      controller.setTime(s"Expire: ${d.expiryDate}")

      node.setOnMouseClicked(_ => displayDiscount(d))

      DiscountContainer.getChildren.add(node)
    }
  }

  private def displayDiscount(d: Discount): Unit = {
    DiscountLogo.setImage(loadImage(d.logo))
    DiscountBackground.setImage(loadImage(d.backgroundImage))
    Discount.setText(d.discount)
    StoreName.setText(d.storeName)
    DiscountAddress.setText(d.address)
    DiscountCity.setText(d.city)
    ExxpiryDate.setText(d.expiryDate)
    FoodAvailable.setText(d.additionalInformation)
  }

  private def loadImage(fileName: String): Image = {
    val stream = getClass.getResourceAsStream(s"/images/$fileName")
    new Image(stream)
  }
}
