package academy.softserve.guestbook

import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Message(
        @Id
        @GeneratedValue
        var id: Long,
        var username: String,
        var message: String,
        var timestamp: Date
) : Serializable