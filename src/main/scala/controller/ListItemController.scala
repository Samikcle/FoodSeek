package controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.ImageView

class ListItemController {

  @FXML
  private var LogoItem: ImageView = _

  @FXML
  private var NameItem: Label = _

  @FXML
  private var LocationItem: Label = _

  @FXML
  private var OHItem: Label = _

  @FXML
  def initialize(): Unit = {
    // This will run after the FXML is loaded
  }
}
