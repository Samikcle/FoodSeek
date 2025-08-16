package controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.stage.Stage

class ClaimPopUpController {
  @FXML
  private var CloseButton: Button = _

  @FXML
  def initialize(): Unit = {
    CloseButton.setOnAction(_ => close())
  }

  private def close(): Unit = {
    val stage = CloseButton.getScene.getWindow.asInstanceOf[Stage]
    stage.close()
  }
}
