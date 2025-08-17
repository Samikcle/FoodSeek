package controller

import javafx.fxml.FXML
import javafx.scene.control.{Button, CheckBox, Label, ScrollPane, TextArea, TextField}
import javafx.scene.layout.HBox

import java.time.{LocalDate, LocalDateTime}

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
  private var NameSection: HBox = _

  @FXML
  private var PickUpName: TextField = _

  @FXML
  private var PickUpPhone: TextField = _

  @FXML
  private var PickUpNotes: TextArea = _

  @FXML
  private var PickUpError: Label = _

  @FXML
  private var SuccessTextRequest: Label = _

  @FXML
  private var SuccessRequest: Label = _

  @FXML
  private var RequestContents: ScrollPane = _

  @FXML
  def initialize(): Unit = {
    PickUpError.setVisible(false)
    PickUpError.setManaged(false)

    PickUpOther.setOnAction(_ => {
      NameSection.setDisable(!PickUpOther.isSelected)
    })
  }

  def setFoodbankName(name: String): Unit = FoodbankName.setText(name)
  def setFoodbankLocation(location: String): Unit = FoodbankLocation.setText(location)

  def validateForm(): Unit = {
    val dayStr = PickUpDay.getText.trim
    val monthStr = PickUpMonth.getText.trim
    val yearStr = PickUpYear.getText.trim

    if (dayStr.isEmpty || monthStr.isEmpty || yearStr.isEmpty) {
      showError("Date cannot be empty")
      return
    }

    val dayOpt = parseIntSafe(dayStr)
    val monthOpt = parseIntSafe(monthStr)
    val yearOpt = parseIntSafe(yearStr)

    if (dayOpt.isEmpty || monthOpt.isEmpty || yearOpt.isEmpty) {
      showError("Date format incorrect")
      return
    }

    val day = dayOpt.get
    val month = monthOpt.get
    val year = yearOpt.get

    if (day < 1 || day > 31 || month < 1 || month > 12) {
      showError("Date format incorrect")
      return
    }

    val today = LocalDate.now()
    val enteredDate = LocalDate.of(year, month, day)
    if (!enteredDate.isAfter(today)) {
      showError("Date must be after today")
      return
    }

    val hourStr = PickUpHour.getText.trim
    val minuteStr = PickUpMinute.getText.trim

    if (hourStr.isEmpty || minuteStr.isEmpty) {
      showError("Time cannot be empty")
      return
    }

    val hourOpt = parseIntSafe(hourStr)
    val minuteOpt = parseIntSafe(minuteStr)

    if (hourOpt.isEmpty || minuteOpt.isEmpty) {
      showError("Time format incorrect")
      return
    }

    val hour = hourOpt.get
    val minute = minuteOpt.get

    if (hour < 0 || hour > 24 || minute < 0 || minute > 59) {
      showError("Time format incorrect")
      return
    }

    if (PickUpOther.isSelected) {
      if (PickUpName.getText.trim.isEmpty) {
        showError("Name cannot be empty")
        return
      }
      if (PickUpPhone.getText.trim.isEmpty) {
        showError("Phone cannot be empty")
        return
      }
    }

    RequestContents.setContent(null)
    SuccessTextRequest.setVisible(true)
    SuccessRequest.setVisible(true)
  }

  private def showError(msg: String): Unit = {
    PickUpError.setText(msg)
    PickUpError.setVisible(true)
    PickUpError.setManaged(true)
  }

  private def parseIntSafe(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case _: NumberFormatException => None
    }
  }

}
