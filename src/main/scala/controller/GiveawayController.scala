package controller

import main.MyApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.*
import javafx.scene.image.*
import javafx.scene.layout.VBox
import javafx.scene.Node
import javafx.collections.FXCollections
import scala.jdk.CollectionConverters.*
import scalafx.Includes.*
import model.{Activity, Event}
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GiveawayController {

  @FXML
  private var EventLocationSelect: ComboBox[String] = _

  @FXML
  private var EventBackground: ImageView = _

  @FXML
  private var EventLogo: ImageView = _

  @FXML
  private var EventInfo: Label = _

  @FXML
  private var EventAddress: Label = _

  @FXML
  private var EventCity: Label = _

  @FXML
  private var EventFood: Label = _

  @FXML
  private var Name: Label = _

  @FXML
  private var EventDate: Label = _

  @FXML
  private var EventTime: Label = _

  @FXML
  private var EventContainer: VBox = _

  @FXML
  private var AddToActivities: Button = _

  private var events: List[Event] = List()
  private var cities: List[String] = List()
  private var currentEvent: Event = _

  private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  @FXML
  def initialize(): Unit = {
    setEvents(MyApp.events, MyApp.citiesList)
  }

  private def setEvents(list: List[Event], citiesList: List[String]): Unit = {
    events = list
    cities = citiesList

    val today = LocalDate.now()
    events = list.filter(event => {
      val eventDate = LocalDate.parse(event.date, dateFormat)
      eventDate.isAfter(today)
    })

    events = events.sortBy(ev => LocalDate.parse(ev.date, dateFormat))

    setupCityComboBox()
    loadListItems(events)
    if (events.nonEmpty) {
      displayEvent(events.head)
    }
  }

  private def setupCityComboBox(): Unit = {
    EventLocationSelect.setItems(FXCollections.observableArrayList(cities.asJava))

    EventLocationSelect.setOnAction(_ => {
      val selectedCity = Option(EventLocationSelect.getValue).getOrElse("")
      if (selectedCity.trim.isEmpty) {
        loadListItems(events)
      } else {
        val filtered = events.filter(_.city.equalsIgnoreCase(selectedCity.trim))
        loadListItems(filtered)
      }
    })
  }

  private def loadListItems(list: List[Event]): Unit = {
    EventContainer.getChildren.clear()
    list.foreach { ev =>
      val loader = new FXMLLoader(getClass.getResource("/view/ListItem.fxml"))
      val node: Node = loader.load()
      val controller = loader.getController[ListItemController]

      controller.setLogo(loadImage(ev.logo))
      controller.setName(ev.name)
      controller.setCity(ev.city)
      controller.setTime(ev.date)

      node.setOnMouseClicked(_ => displayEvent(ev))

      EventContainer.getChildren.add(node)
    }
  }

  private def displayEvent(ev: Event): Unit = {
    currentEvent = ev

    EventLogo.setImage(loadImage(ev.logo))
    EventBackground.setImage(loadImage(ev.backgroundImage))
    Name.setText(ev.name)
    EventAddress.setText(ev.address)
    EventCity.setText(ev.city)
    EventDate.setText(ev.date)
    EventTime.setText(ev.time)
    EventFood.setText(ev.foodAvailable)
    EventInfo.setText(ev.additionalInformation)
  }

  private def loadImage(fileName: String): Image = {
    val stream = getClass.getResourceAsStream(s"/images/$fileName")
    new Image(stream)
  }

  def addEventAsActivity(): Unit = {
    if (currentEvent == null) return

    val newId = MyApp.activities.size
    val newActivity = Activity(
      id = newId,
      userID = MyApp.currentUserID,
      name = null,
      phone = null,
      ownerID = -1,
      activityName = currentEvent.name,
      address = currentEvent.address,
      city = currentEvent.city,
      date = currentEvent.date,
      time = currentEvent.time,
      notes = null,
      status = null
    )

    MyApp.addActivity(newActivity)
  }

}
