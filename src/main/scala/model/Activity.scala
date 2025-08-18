package model

case class Activity(
                     id: Int,
                     userID: Int,
                     name: String,
                     phone: String,
                     ownerID: Int,
                     activityName: String,
                     address: String,
                     city: String,
                     date: String,
                     time: String,
                     notes: String,
                     status: String // pending, approved, rejected
                   )
