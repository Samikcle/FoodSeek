package model

case class Event(
                  id: Int,
                  logo: String,
                  backgroundImage: String,
                  name: String,
                  address: String,
                  city: String,
                  date: String,
                  time: String,
                  foodAvailable: String,
                  additionalInformation: String
)
