package controller

import main.MyApp
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.*
import javafx.scene.image.*
import javafx.scene.layout.{AnchorPane, Pane, VBox}
import model.Foodbank
import javafx.scene.Node
import javafx.collections.FXCollections
import javafx.scene.Parent
import scala.jdk.CollectionConverters.*
import scalafx.scene.Scene
import scalafx.stage.{Modality, Stage}
import scalafx.Includes.*

class FoodbanksController {

  @FXML
  private var LocationSelector: ComboBox[String] = _

  @FXML
  private var BackgroundImage: ImageView = _

  @FXML
  private var Logo: ImageView = _

  @FXML
  private var AdditionalInfo: Label = _

  @FXML
  private var Address: Label = _

  @FXML
  private var City: Label = _

  @FXML
  private var FoodAvailable: Label = _

  @FXML
  private var Name: Label = _

  @FXML
  private var OperatingHour: Label = _

  @FXML
  private var Phone: Label = _

  @FXML
  private var ItemContainer: VBox = _

  @FXML
  private var Claimable: Button = _

  @FXML
  private var PickUp: Button = _

  @FXML
  private var BackButton: Pane = _

  @FXML
  private var FoodBankContent: AnchorPane = _

  private var foodbanks: List[Foodbank] = List()
  private var cities: List[String] = List()
  private var originalContent: Node = _

  @FXML
  def initialize(): Unit = {
    setFoodbanks(MyApp.foodbanks, MyApp.citiesList)

    BackButton.setOnMouseClicked(_ => restoreOriginalPage())
    BackButton.setVisible(false)
    BackButton.setManaged(false)
    BackButton.setDisable(true)
  }

  private def setFoodbanks(list: List[Foodbank], citiesList: List[String]): Unit = {
    foodbanks = list
    cities = citiesList
    setupCityComboBox()
    loadListItems(foodbanks)
    if (foodbanks.nonEmpty) {
      displayFoodbank(foodbanks.head)
    }
  }

  private def setupCityComboBox(): Unit = {
    LocationSelector.setItems(FXCollections.observableArrayList(cities.asJava))

    // Filtering when a city is selected
    LocationSelector.setOnAction(_ => {
      val selectedCity = Option(LocationSelector.getValue).getOrElse("")
      if (selectedCity.trim.isEmpty) {
        loadListItems(foodbanks)
      } else {
        val filtered = foodbanks.filter(_.city.equalsIgnoreCase(selectedCity.trim))
        loadListItems(filtered)
      }
    })
  }

  private def loadListItems(list: List[Foodbank]): Unit = {
    ItemContainer.getChildren.clear()
    list.foreach { fb =>
      val loader = new FXMLLoader(getClass.getResource("/view/ListItem.fxml"))
      val node: Node = loader.load()
      val controller = loader.getController[ListItemController]

      controller.setLogo(loadImage(fb.logo))
      controller.setName(fb.name)
      controller.setCity(fb.city)
      controller.setTime(fb.operatingHour)

      node.setOnMouseClicked(_ => displayFoodbank(fb))

      ItemContainer.getChildren.add(node)
    }
  }

  private def displayFoodbank(fb: Foodbank): Unit = {
    Logo.setImage(loadImage(fb.logo))
    BackgroundImage.setImage(loadImage(fb.backgroundImage))
    Name.setText(fb.name)
    Address.setText(fb.address)
    City.setText(fb.city)
    Phone.setText(fb.phone)
    OperatingHour.setText(fb.operatingHour)
    FoodAvailable.setText(fb.foodAvailable)
    AdditionalInfo.setText(fb.additionalInformation)

    if(fb.owner == -1){
      PickUp.setVisible(false)
      PickUp.setManaged(false)
      PickUp.setDisable(true)

      Claimable.setVisible(true)
      Claimable.setManaged(true)
      Claimable.setDisable(false)
    }
    else{
      PickUp.setVisible(true)
      PickUp.setManaged(true)
      PickUp.setDisable(false)

      Claimable.setVisible(false)
      Claimable.setManaged(false)
      Claimable.setDisable(true)
    }


  }

  private def loadImage(fileName: String): Image = {
    val stream = getClass.getResourceAsStream(s"/images/$fileName")
    new Image(stream)
  }

  def openClaimPopup(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("/view/ClaimPopUp.fxml"))
    val root: Parent = loader.load()

    val popupStage = new Stage() {
      title = "Claim this Business"
      initModality(Modality.ApplicationModal)
      scene = new Scene(root)
    }

    popupStage.showAndWait()
  }

  def openPickUpForm(): Unit = {
    if (!FoodBankContent.getChildren.isEmpty) {
      originalContent = new AnchorPane()
      originalContent.asInstanceOf[AnchorPane].getChildren.addAll(FoodBankContent.getChildren)
    }

    val loader = new FXMLLoader(getClass.getResource("/view/PickUpForm.fxml"))
    val formContent: Node = loader.load()
    val formController = loader.getController[PickUpFormController]

    formController.setFoodbankName(Name.getText)
    formController.setFoodbankLocation(s"${Address.getText}, ${City.getText}")

    FoodBankContent.getChildren.clear()
    FoodBankContent.getChildren.add(formContent)
    FoodBankContent.getChildren.add(BackButton)

    BackButton.setVisible(true)
    BackButton.setManaged(true)
    BackButton.setDisable(false)
  }

  private def restoreOriginalPage(): Unit = {
    FoodBankContent.getChildren.clear()
    if (originalContent != null) {
      FoodBankContent.getChildren.addAll(originalContent.asInstanceOf[AnchorPane].getChildren)
    }
    BackButton.setVisible(false)
    BackButton.setManaged(false)
  }
}

