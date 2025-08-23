package controller

import javafx.fxml.FXML
import javafx.scene.control.{Button, Label, PasswordField, TextField}
import javafx.scene.layout.HBox
import main.MyApp
import model.User
import javafx.scene.Scene
import scalafx.stage.Stage

class LoginController {

  @FXML
  private var LoginPageButton: HBox = _

  @FXML
  private var SignUpPageButton: HBox = _

  @FXML
  private var LoginUsername: TextField = _

  @FXML
  private var LoginPassword: PasswordField = _

  @FXML
  private var LoginConfirmPasswordLabel: Label = _

  @FXML
  private var LoginConfirmPassword: PasswordField = _

  @FXML
  private var LoginNameLabel: Label = _

  @FXML
  private var LoginName: TextField = _

  @FXML
  private var LoginPhoneLabel: Label = _

  @FXML
  private var LoginPhone: TextField = _

  @FXML
  private var LoginError: Label = _

  @FXML
  private var LoginButton: Button = _

  @FXML
  def initialize(): Unit = {
    switchToLogin()

    LoginPageButton.setOnMouseClicked(_ => switchToLogin())
    SignUpPageButton.setOnMouseClicked(_ => switchToSignup())
  }

  private def switchToLogin(): Unit = {
    LoginConfirmPasswordLabel.setVisible(false)
    LoginConfirmPassword.setVisible(false)
    LoginNameLabel.setVisible(false)
    LoginName.setVisible(false)
    LoginPhoneLabel.setVisible(false)
    LoginPhone.setVisible(false)
    LoginError.setVisible(false)

    LoginPageButton.setStyle("-fx-background-color: lightgray;")
    SignUpPageButton.setStyle("-fx-background-color: gray;")

    LoginButton.setText("Login")
    LoginButton.setOnAction(_ => handleLogin())
  }

  private def switchToSignup(): Unit = {
    LoginConfirmPasswordLabel.setVisible(true)
    LoginConfirmPassword.setVisible(true)
    LoginNameLabel.setVisible(true)
    LoginName.setVisible(true)
    LoginPhoneLabel.setVisible(true)
    LoginPhone.setVisible(true)

    SignUpPageButton.setStyle("-fx-background-color: lightgray;")
    LoginPageButton.setStyle("-fx-background-color: gray;")

    LoginButton.setText("Sign Up")
    LoginButton.setOnAction(_ => handleSignup())
  }

  private def handleLogin(): Unit = {
    val username = LoginUsername.getText.trim
    val password = LoginPassword.getText.trim

    LoginError.setVisible(true)

    if (username.isEmpty || password.isEmpty) {
      LoginError.setText("Username or Password cannot be empty")
      return
    }

    val maybeAccount = MyApp.accounts.find(_.login(username, password))

    maybeAccount match {
      case Some(account) =>
        MyApp.currentUserID = account.userID

        val root = account.setupLayout()
        MyApp.setScene(root)

      case None =>
        LoginError.setText("Username or Password incorrect")
    }
  }

  private def handleSignup(): Unit = {
    val username = LoginUsername.getText.trim
    val password = LoginPassword.getText.trim
    val confirmPassword = LoginConfirmPassword.getText.trim
    val name = LoginName.getText.trim
    val phone = LoginPhone.getText.trim

    LoginError.setVisible(true)

    if (username.isEmpty || password.isEmpty || confirmPassword.isEmpty || name.isEmpty || phone.isEmpty) {
      LoginError.setText("Make sure all fields are filled")
      return
    }

    if (password != confirmPassword) {
      LoginError.setText("Passwords do not match")
      return
    }

    if (MyApp.accounts.exists(_.getUsername == username)) {
      LoginError.setText("Username already exists")
      return
    }

    val newId = MyApp.accounts.size
    val newUser = new User(newId, username, password, name, phone, "User")
    MyApp.newAccount(newUser)

    MyApp.currentUserID = newId

    val root = newUser.setupLayout()
    MyApp.setScene(root)

  }
}
