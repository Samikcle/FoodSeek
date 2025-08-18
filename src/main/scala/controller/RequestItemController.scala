package controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.{AnchorPane, Pane}
import javafx.scene.input.MouseEvent
import model.Activity
import main.MyApp

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
  private var RequestContent: AnchorPane = _

  private var currentActivity: Activity = _

  @FXML
  def initialize(): Unit = {
    RequestApprove.setOnMouseClicked(_ => approveRequest())
    RequestReject.setOnMouseClicked(_ => rejectRequest())
  }

  def setActivity(activity: Activity): Unit = {
    currentActivity = activity
    RequestName.setText(activity.name)
    RequestPhone.setText(activity.phone)
    RequestTime.setText(s"${activity.date} ${activity.time}")
    RequestNote.setText(activity.notes)
  }

  private def approveRequest(): Unit = {
    if (currentActivity != null) {
      val updated = currentActivity.copy(status = "Approved")
      MyApp.updateActivity(updated)
      removeFromUI()
    }
  }

  private def rejectRequest(): Unit = {
    if (currentActivity != null) {
      val updated = currentActivity.copy(status = "Rejected")
      MyApp.updateActivity(updated)
      removeFromUI()
    }
  }

  private def removeFromUI(): Unit = {
    val node = RequestName.getParent
    if (node != null && node.getParent != null) {
      node.getParent.asInstanceOf[javafx.scene.layout.Pane].getChildren.remove(node)
    }
  }
}
