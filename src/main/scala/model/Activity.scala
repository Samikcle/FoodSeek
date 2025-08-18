package model

case class Activity(
                     id: Int,
                     userID: Int,
                     name: String,
                     phone: String,
                     ownerID: Int,
                     activityName: String,
                     date: String,
                     time: String,
                     address: String,
                     city: String,
                     notes: String,
                     status: String
                   )
