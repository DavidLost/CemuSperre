package de.david.lock

import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import java.io.BufferedReader
import java.io.InputStreamReader

class SftpServerReader(args: Args) : JSch() {

    open class Args(val username: String, val userpassword: String, val serverdomain: String, val port: Int)

    //constructor(username: String, userpassword: String, serverdomain: String, port: Int) : this(Args(username, userpassword, serverdomain, port))

    companion object {
        val LUCAS_SERVER_ARGS_FILE_PATH = "D:\\Bibliotheken\\Dokumente\\Stuff\\topSecret\\LucasServerArgs.txt"
        val ARG_AMOUNT = 4
        fun createArgs(args: Array<String>): Args? {
            if (args.size != ARG_AMOUNT) return null
            return Args(args[0], args[1], args[2], args[3].toInt())
        }
    }

    val session = getSession(args.username, args.serverdomain, args.port)

    init {
        session.setConfig("StrictHostKeyChecking", "no")
        session.setPassword(args.userpassword)
        session.connect()
    }

    fun getFileText(filePath: String): List<String> {
        val sftp = session.openChannel("sftp") as ChannelSftp
        sftp.connect()
        return BufferedReader(InputStreamReader(sftp.get(filePath))).readLines()
    }

}