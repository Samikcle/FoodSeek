package controller

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox

class FoodbanksController {

  @FXML
  private var LocationSelector: ComboBox[String] = _

  @FXML
  private var BackgroundImage: ImageView = _

  @FXML
  private var Logo: ImageView = _

  @FXML
  private var AdditionalInfo: Label = _

  @FXML
  private var Address: Label = _

  @FXML
  private var FoodAvailable: Label = _

  @FXML
  private var Name: Label = _

  @FXML
  private var OperatingHour: Label = _

  @FXML
  private var Phone: Label = _

  @FXML
  private var ItemContainer: VBox = _

  @FXML 
  private var Claimable: Button = _

  @FXML
  def initialize(): Unit = {
    // This will run after the FXML is loaded
  }
}
