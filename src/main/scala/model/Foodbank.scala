package model

case class Foodbank(
                     logo: String,
                     backgroundImage: String,
                     name: String,
                     address: String,
                     city: String,
                     phone: String,
                     operatingHour: String,
                     foodAvailable: String,
                     additionalInformation: String,
                     owner: Int
                   )
