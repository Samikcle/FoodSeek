package model

case class Discount(
                     id: Int,
                     logo: String,
                     backgroundImage: String,
                     discount: String,
                     storeName: String,
                     address: String,
                     city: String,
                     expiryDate: String,
                     additionalInformation: String,
                   )
