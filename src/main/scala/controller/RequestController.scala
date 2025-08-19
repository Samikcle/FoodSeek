package controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.VBox
import javafx.scene.Node
import javafx.scene.control.Label
import model.Activity
import scala.jdk.CollectionConverters.*
import main.MyApp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RequestController {

  @FXML
  private var RequestList: VBox = _

  @FXML
  private var RequestNone: Label = _

  @FXML
  def initialize(): Unit = {
    loadRequests(MyApp.activities.toList)
  }

  private def loadRequests(activities: List[Activity]): Unit = {
    RequestList.getChildren.clear()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val today = LocalDate.now()

    val filtered = activities.filter { activity =>
      val activityDate = LocalDate.parse(activity.date, formatter)
      activity.status == "Pending" &&
        activity.ownerID == MyApp.currentUserID &&
        !activityDate.isBefore(today) // today or after
    }

    if (filtered.isEmpty) {
      RequestNone.setVisible(true)
      RequestNone.setManaged(true)
    } else {
      RequestNone.setVisible(false)
      RequestNone.setManaged(false)

      filtered.foreach { activity =>
        val loader = new FXMLLoader(getClass.getResource("/view/RequestItem.fxml"))
        val node: Node = loader.load()
        val controller = loader.getController[RequestItemController]

        controller.setActivity(activity)

        RequestList.getChildren.add(node)
      }
    }  
  }

}
