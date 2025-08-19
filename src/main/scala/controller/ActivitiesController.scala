package controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.VBox
import javafx.scene.Node
import javafx.scene.control.Label
import main.MyApp
import model.Activity
import scala.jdk.CollectionConverters._
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ActivitiesController {

  @FXML
  private var ActivitiesList: VBox = _

  @FXML
  private var ActivitiesNone: Label = _

  @FXML
  def initialize(): Unit = {
    loadActivities(MyApp.activities.toList)
  }

  private def loadActivities(activities: List[Activity]): Unit = {
    ActivitiesList.getChildren.clear()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val today = LocalDate.now()

    val filtered = activities.filter { activity =>
      val activityDate = LocalDate.parse(activity.date, formatter)
      activity.userID == MyApp.currentUserID && !activityDate.isBefore(today)
    }

    if (filtered.isEmpty) {
      ActivitiesNone.setVisible(true)
      ActivitiesNone.setManaged(true)
    } else {
      ActivitiesNone.setVisible(false)
      ActivitiesNone.setManaged(false)

      filtered.foreach { activity =>
        val loader = new FXMLLoader(getClass.getResource("/view/activityItem.fxml"))
        val node: Node = loader.load()
        val controller = loader.getController[ActivityItemController]

        controller.setName(activity.activityName)
        controller.setStatus(activity.status)
        controller.setTime(s"${activity.date} ${activity.time}")
        controller.setLocation(s"${activity.address}, ${activity.city}")

        ActivitiesList.getChildren.add(node)
      }
    }
  }

}
