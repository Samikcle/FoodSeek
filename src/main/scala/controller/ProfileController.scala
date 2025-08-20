package controller

import javafx.fxml.FXML
import javafx.scene.control.{Label, PasswordField, TextField, Button}
import javafx.scene.layout.{VBox, HBox}
import main.MyApp
import model.Account
import javafx.scene.input.MouseEvent

class ProfileController {

  @FXML
  private var ProfileUsername: Label = _

  @FXML
  private var ProfileChangePassword: HBox = _

  @FXML
  private var PasswordSection: VBox = _

  @FXML
  private var ProfileOldPassword: PasswordField = _

  @FXML
  private var ProfileNewPassword: PasswordField = _

  @FXML
  private var ProfileConfirmPassword: PasswordField = _

  @FXML
  private var ProfilePasswordError: Label = _

  @FXML
  private var ProfilePasswordUpdate: Button = _

  @FXML
  private var ProfileNameEdit: HBox = _

  @FXML
  private var ProfileName: Label = _

  @FXML
  private var NameSection: VBox = _

  @FXML
  private var ProfileNewName: TextField = _

  @FXML
  private var ProfileNameError: Label = _

  @FXML
  private var ProfileNameUpdate: Button = _

  @FXML
  private var ProfilePhoneEdit: HBox = _

  @FXML
  private var ProfilePhone: Label = _

  @FXML
  private var PhoneSection: VBox = _

  @FXML
  private var ProfileNewPhone: TextField = _

  @FXML
  private var ProfilePhoneError: Label = _

  @FXML
  private var ProfilePhoneUpdate: Button = _

  @FXML
  private var ProfileLogout: HBox = _

  private val currentUser: Account = MyApp.accounts(MyApp.currentUserID)

  @FXML
  def initialize(): Unit = {
    PasswordSection.setVisible(false)
    PasswordSection.setManaged(false)

    NameSection.setVisible(false)
    NameSection.setManaged(false)

    PhoneSection.setVisible(false)
    PhoneSection.setManaged(false)

    ProfilePasswordError.setVisible(false)
    ProfileNameError.setVisible(false)
    ProfilePhoneError.setVisible(false)
    ProfilePasswordError.setManaged(false)
    ProfileNameError.setManaged(false)
    ProfilePhoneError.setManaged(false)

    if (currentUser != null) {
      ProfileUsername.setText(currentUser.getUsername)
      ProfileName.setText(currentUser.name)
      ProfilePhone.setText(currentUser.phoneNo)
    }

    ProfileChangePassword.setOnMouseClicked((_: MouseEvent) => showPasswordSection())
    ProfileNameEdit.setOnMouseClicked((_: MouseEvent) => showNameSection())
    ProfilePhoneEdit.setOnMouseClicked((_: MouseEvent) => showPhoneSection())
    ProfileLogout.setOnMouseClicked((_: MouseEvent) => handleLogout())
  }

  private def showPasswordSection(): Unit = {
    PasswordSection.setVisible(true)
    PasswordSection.setManaged(true)
  }

  private def showNameSection(): Unit = {
    NameSection.setVisible(true)
    NameSection.setManaged(true)
  }

  private def showPhoneSection(): Unit = {
    PhoneSection.setVisible(true)
    PhoneSection.setManaged(true)
  }

  def changePassword(): Unit = {
    val oldPass = ProfileOldPassword.getText
    val newPass = ProfileNewPassword.getText
    val confirmPass = ProfileConfirmPassword.getText

    ProfilePasswordError.setVisible(true)
    ProfilePasswordError.setManaged(true)

    if (newPass != confirmPass) {
      ProfilePasswordError.setText("Passwords do not match")
      ProfilePasswordError.setStyle("-fx-text-fill: red;")
    } else if (!currentUser.changePassword(oldPass, newPass)) {
      ProfilePasswordError.setText("Old password incorrect")
      ProfilePasswordError.setStyle("-fx-text-fill: red;")
    } else {
      ProfilePasswordError.setText("Success")
      ProfilePasswordError.setStyle("-fx-text-fill: green;")

      ProfileOldPassword.clear()
      ProfileNewPassword.clear()
      ProfileConfirmPassword.clear()
    }
  }

  def changeName(): Unit = {
    val newName = ProfileNewName.getText.trim

    ProfileNameError.setVisible(true)
    ProfileNameError.setManaged(true)

    if (newName.isEmpty) {
      ProfileNameError.setText("Name cannot be empty")
      ProfileNameError.setStyle("-fx-text-fill: red;")
    } else {
      currentUser.name = newName
      ProfileName.setText(newName)

      ProfileNameError.setText("Success")
      ProfileNameError.setStyle("-fx-text-fill: green;")

      ProfileNewName.clear()
      MyApp.saveAccountsToFile("src/main/resources/data/accounts.txt")
    }
  }

  def changePhone(): Unit = {
    val newPhone = ProfileNewPhone.getText.trim

    ProfilePhoneError.setVisible(true)
    ProfilePhoneError.setManaged(true)

    if (newPhone.isEmpty) {
      ProfilePhoneError.setText("Phone cannot be empty")
      ProfilePhoneError.setStyle("-fx-text-fill: red;")

    } else {
      currentUser.phoneNo = newPhone
      ProfilePhone.setText(newPhone)

      ProfilePhoneError.setText("Success")
      ProfilePhoneError.setStyle("-fx-text-fill: green;")
      MyApp.saveAccountsToFile("src/main/resources/data/accounts.txt")
    }
  }

  private def handleLogout(): Unit = {
    import javafx.fxml.FXMLLoader

    val loader = new FXMLLoader(getClass.getResource("/view/Login.fxml"))
    val root: javafx.scene.Parent = loader.load()
    MyApp.setScene(root)
  }

}
