package model

case class Foodbank(
                     logo: String,
                     backgroundImage: String,
                     name: String,
                     address: String,
                     phone: String,
                     operatingHour: String,
                     foodAvailable: String,
                     additionalInformation: String,
                     claimed: Boolean
                   )
