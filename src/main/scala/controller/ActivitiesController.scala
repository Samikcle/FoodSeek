package controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.VBox
import javafx.scene.Node
import main.MyApp
import model.Activity
import scala.jdk.CollectionConverters._

class ActivitiesController {

  @FXML 
  private var ActivitiesList: VBox = _

  @FXML
  def initialize(): Unit = {
    loadActivities(MyApp.activities.toList)
  }

  private def loadActivities(activities: List[Activity]): Unit = {
    ActivitiesList.getChildren.clear()

    activities.foreach { activity =>
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
