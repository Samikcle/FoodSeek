package controller

import javafx.fxml.FXML
import javafx.scene.control.{ComboBox, Label, TextArea, TextField}
import main.MyApp
import javafx.collections.FXCollections
import scala.jdk.CollectionConverters._
import model.Foodbank

class ManageController {

  @FXML
  private var ManageFoodbank: TextField = _

  @FXML
  private var ManageAddress: TextArea = _

  @FXML
  private var ManageCity: ComboBox[String] = _

  @FXML
  private var ManagePhone: TextField = _

  @FXML
  private var ManageDay1: ComboBox[String] = _

  @FXML
  private var ManageDay2: ComboBox[String] = _

  @FXML
  private var ManageHour1: TextField = _

  @FXML
  private var ManageMinute1: TextField = _

  @FXML
  private var ManageHour2: TextField = _

  @FXML
  private var ManageMinute2: TextField = _

  @FXML
  private var ManageFood: TextArea = _

  @FXML
  private var ManageInfo: TextArea = _

  @FXML
  private var ManageError: Label = _

  private var currentFoodbank: Foodbank = null

  @FXML
  def initialize(): Unit = {
    ManageError.setManaged(false)
    ManageError.setVisible(false)
    ManageCity.setItems(FXCollections.observableArrayList(MyApp.citiesList.asJava))

    val days = List("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    ManageDay1.setItems(FXCollections.observableArrayList(days.asJava))
    ManageDay2.setItems(FXCollections.observableArrayList(days.asJava))
    currentFoodbank = MyApp.foodbanks(1)
    loadFoodbank(currentFoodbank)
  }

  private def loadFoodbank(fb: Foodbank): Unit = {
    ManageFoodbank.setText(fb.name)
    ManageAddress.setText(fb.address)
    ManageCity.setValue(fb.city)
    ManagePhone.setText(fb.phone)
    ManageFood.setText(fb.foodAvailable)
    ManageInfo.setText(fb.additionalInformation)

    val parts = fb.operatingHour.split(",").map(_.trim)
    if (parts.length == 2) {
      val daysRange = parts(0)
      val timeRange = parts(1)

      val dayParts = daysRange.split("-")
      if (dayParts.length == 2) {
        ManageDay1.setValue(dayParts(0))
        ManageDay2.setValue(dayParts(1))
      }

      val timeParts = timeRange.split("-")
      if (timeParts.length == 2) {
        val start = timeParts(0).split(":")
        val end = timeParts(1).split(":")

        if (start.length == 2) {
          ManageHour1.setText(start(0))
          ManageMinute1.setText(start(1))
        }
        if (end.length == 2) {
          ManageHour2.setText(end(0))
          ManageMinute2.setText(end(1))
        }
      }
    }
  }

  def updateFoodbank(): Unit = {
    if (ManageFoodbank.getText.trim.isEmpty ||
      ManageAddress.getText.trim.isEmpty ||
      ManageCity.getValue == null ||
      ManagePhone.getText.trim.isEmpty ||
      ManageDay1.getValue == null ||
      ManageDay2.getValue == null ||
      ManageHour1.getText.trim.isEmpty ||
      ManageMinute1.getText.trim.isEmpty ||
      ManageHour2.getText.trim.isEmpty ||
      ManageMinute2.getText.trim.isEmpty ||
      ManageFood.getText.trim.isEmpty ||
      ManageInfo.getText.trim.isEmpty) {

      ManageError.setText("Make sure all fields are filled")
      ManageError.setStyle("-fx-text-fill: red;")
      ManageError.setManaged(true)
      ManageError.setVisible(true)
      return
    }

    val (hour1, min1, hour2, min2) = try {
      (
        ManageHour1.getText.toInt,
        ManageMinute1.getText.toInt,
        ManageHour2.getText.toInt,
        ManageMinute2.getText.toInt
      )
    } catch {
      case _: NumberFormatException =>
        ManageError.setText("Time format incorrect")
        ManageError.setStyle("-fx-text-fill: red;")
        ManageError.setManaged(true)
        ManageError.setVisible(true)
        return
    }

    if (hour1 < 0 || hour1 > 24 || hour2 < 0 || hour2 > 24 ||
      min1 < 0 || min1 > 59 || min2 < 0 || min2 > 59) {
      ManageError.setText("Time format incorrect")
      ManageError.setStyle("-fx-text-fill: red;")
      ManageError.setManaged(true)
      ManageError.setVisible(true)
      return
    }

    ManageError.setText("Success")
    ManageError.setStyle("-fx-text-fill: green;")
    ManageError.setManaged(true)
    ManageError.setVisible(true)

    val updatedFoodbank = Foodbank(
      id = currentFoodbank.id,
      logo = currentFoodbank.logo,
      backgroundImage = currentFoodbank.backgroundImage,
      name = ManageFoodbank.getText.trim,
      address = ManageAddress.getText.trim,
      city = ManageCity.getValue,
      phone = ManagePhone.getText.trim,
      operatingHour = s"${ManageDay1.getValue}-${ManageDay2.getValue}, " +
        f"${ManageHour1.getText}%s:${ManageMinute1.getText}%s-${ManageHour2.getText}%s:${ManageMinute2.getText}%s",
      foodAvailable = ManageFood.getText.trim,
      additionalInformation = ManageInfo.getText.trim,
      owner = currentFoodbank.owner
    )

    MyApp.updateFoodbank(updatedFoodbank)
    currentFoodbank = updatedFoodbank
  }

}
