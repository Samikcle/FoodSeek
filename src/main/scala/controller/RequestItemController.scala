package controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class RequestItemController {

  @FXML
  private var RequestName: Label = _

  @FXML
  private var RequestPhone: Label = _

  @FXML
  private var RequestTime: Label = _

  @FXML
  private var RequestNote: Label = _

  @FXML
  private var RequestApprove: Pane = _

  @FXML
  private var RequestReject: Pane = _

  @FXML
  def initialize(): Unit = {
  }
}
