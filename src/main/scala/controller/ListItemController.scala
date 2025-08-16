package controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.{Image, ImageView}

class ListItemController {

  @FXML
  private var LogoItem: ImageView = _

  @FXML
  private var NameItem: Label = _

  @FXML
  private var CityItem: Label = _

  @FXML
  private var TimeItem: Label = _

  @FXML
  def initialize(): Unit = { }

  def setLogo(image: Image): Unit = LogoItem.setImage(image)
  def setName(name: String): Unit = NameItem.setText(name)
  def setCity(city: String): Unit = CityItem.setText(city)
  def setTime(time: String): Unit = TimeItem.setText(time)
}
