package controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Node
import javafx.scene.layout.Pane

class LayoutController {
  @FXML private var Content: Pane = _

  @FXML private var FoodBankMenu: Pane = _
  @FXML private var GiveawayMenu: Pane = _
  @FXML private var DiscountMenu: Pane = _
  @FXML private var ManageMenu: Pane = _
  @FXML private var RequestMenu: Pane = _
  @FXML private var Profile: Pane = _

  private var currentMenu: Pane = null;

  @FXML
  def initialize(): Unit = {
    loadPage(FoodBankMenu, "/view/Foodbanks.fxml")

    FoodBankMenu.setOnMouseClicked(_ => loadPage(FoodBankMenu, "/view/Foodbanks.fxml"))
    GiveawayMenu.setOnMouseClicked(_ => loadPage(GiveawayMenu, "/view/Giveaway.fxml"))
    DiscountMenu.setOnMouseClicked(_ => loadPage(DiscountMenu, "/view/Discount.fxml"))
    ManageMenu.setOnMouseClicked(_ => loadPage(ManageMenu, "/view/Manage.fxml"))
    RequestMenu.setOnMouseClicked(_ => loadPage(RequestMenu, "/view/Requests.fxml"))
    Profile.setOnMouseClicked(_ => loadPage(Profile, "/view/Profile.fxml"))
  }

  private def loadPage(menu: Pane, fxmlPath: String): Unit = {
    if (currentMenu != null) {
      currentMenu.setStyle("") // remove background
    }
    menu.setStyle("-fx-background-color: gray;")
    currentMenu = menu;
    val loader = new FXMLLoader(getClass.getResource(fxmlPath))
    val content: Node = loader.load()
    Content.getChildren.clear()
    Content.getChildren.add(content)
  }

  def disableAdminMenus(): Unit = {
    ManageMenu.setVisible(false)
    ManageMenu.setManaged(false)
    ManageMenu.setDisable(true)

    RequestMenu.setVisible(false)
    RequestMenu.setManaged(false)
    RequestMenu.setDisable(true)
  }
}
