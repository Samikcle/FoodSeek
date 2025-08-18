package controller

import javafx.fxml.FXML
import javafx.scene.control.Label

class ActivityItemController {

  @FXML
  private var ActivityName: Label = _

  @FXML
  private var ActivityStatus: Label = _

  @FXML
  private var ActivityLocation: Label = _

  @FXML
  private var ActivityTime: Label = _

  @FXML
  def initialize(): Unit = {}

  def setName(name: String): Unit = ActivityName.setText(name)
  def setStatus(status: String): Unit = ActivityStatus.setText(status)
  def setLocation(location: String): Unit = ActivityLocation.setText(location)
  def setTime(time: String): Unit = ActivityTime.setText(time)
}
