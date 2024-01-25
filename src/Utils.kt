import models.User
import kotlin.math.abs
import kotlin.math.min

class Utils {
    companion object {
        fun isApproxEqual(v1: Double, v2: Double): Boolean {
            return (abs(v1-v2) / min(abs(v1), abs(v2))) < 1e-10
        }

        fun roundOff(value: Double): Double {
            return (value * 100).toLong() / 100.0
        }

        fun getUser(uid: String): User {
            val bk = BookKeeper
            lateinit var user: User
            if(bk.users[uid.toLong()] != null) {
                user = bk.users[uid.toLong()]!!
            }
            return user
        }
    }

}