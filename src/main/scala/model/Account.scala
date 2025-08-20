package model

import controller.LayoutController
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import main.MyApp

abstract class Account (
                val userID: Int,
                private var username: String,
                private var password: String,
                var name: String,
                var phoneNo: String,
                var accountType: String
              ) {

  def login(inputUsername: String, inputPassword: String): Boolean = {
    username == inputUsername && password == inputPassword
  }

  def getUsername: String = username

  def changePassword(oldPassword: String, newPassword:String):Boolean = {
    if(oldPassword == password){
      password = newPassword
      MyApp.saveAccountsToFile("src/main/resources/data/accounts.txt")
      return true
    }
    false
  }

  def setupLayout(): Parent  = {
    val loader = new FXMLLoader(getClass.getResource("/view/Layout.fxml"))
    val root = loader.load[javafx.scene.Parent]()
    val controller = loader.getController[LayoutController]
    root
  }

  def getPassword: String = password

}
