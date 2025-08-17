package controller

import javafx.fxml.FXML
import javafx.scene.control.{Button, CheckBox, Label, TextArea, TextField}

class PickUpFormController {

  @FXML
  private var FoodbankName: Label = _

  @FXML
  private var FoodbankLocation: Label = _

  @FXML
  private var PickUpDay: TextField = _

  @FXML
  private var PickUpMonth: TextField = _

  @FXML
  private var PickUpYear: TextField = _

  @FXML
  private var PickUpHour: TextField = _

  @FXML
  private var PickUpMinute: TextField = _

  @FXML
  private var PickUpOther: CheckBox = _

  @FXML
  private var PickUpName: TextField = _

  @FXML
  private var PickUpNotes: TextArea = _

  @FXML
  private var PickUpError: Label = _

  @FXML
  private var Submit: Button = _

  @FXML
  def initialize(): Unit = {

  }

}
