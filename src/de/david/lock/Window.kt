package de.david.lock

import java.awt.*
import java.awt.event.ActionEvent
import java.io.File
import java.security.MessageDigest.getInstance
import java.security.NoSuchAlgorithmException
import javax.swing.*
import javax.xml.bind.DatatypeConverter
import kotlin.system.exitProcess


class Window : JFrame() {

    companion object {
        val PASSWORD_FILE_PATH = "/home/david/CemuPasswortHash"
        val EXECUTION_PATH = "D:\\Games\\cemu_1.16.1\\cemu.exe"
    }

    private val label = JLabel("Passwort:")
    private val field = JPasswordField()
    private val button = JButton("OK")

    init {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

        layout = BorderLayout()
        val image = JLabel(ImageIcon("D:\\Bibliotheken\\Bilder\\Zeug\\MarioKart.png"))
        image.layout = GridLayout()
        label.foreground = Color.WHITE
        label.font = Font("Calibri", Font.BOLD, 32)
        image.add(label, Component.TOP_ALIGNMENT)
        image.add(field, Component.CENTER_ALIGNMENT)
        image.add(button, Component.BOTTOM_ALIGNMENT)
        add(image)
        field.addActionListener { onButtonClick() }
        button.addActionListener { onButtonClick() }

        title = "Cemu Sperre :)"
        defaultCloseOperation = EXIT_ON_CLOSE
        isAlwaysOnTop = true
        isResizable = false
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }

    private fun onButtonClick() {
        val userHash =123
        val args = Array(SftpServerReader.ARG_AMOUNT) { i -> serverArgs[i].split(": ").last() }
        val serverReader = SftpServerReader(SftpServerReader.createArgs(args)!!)
        val passwordHash = 123
        if (userHash == passwordHash) {
            Runtime.getRuntime().exec(EXECUTION_PATH)
            exitProcess(0)
        }
        else {
            JOptionPane.showMessageDialog(this, "Das war wohl falsch :(", "Error", JOptionPane.ERROR_MESSAGE)
        }
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun getSHA256(input: String): ByteArray {
        val md = getInstance("SHA-256")
        return md.digest(input.toByteArray())
    }

}