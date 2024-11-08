package com.example.odontovision.login

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.odontovision.R
import java.util.*

class PrimeiroAcessoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primeiro_acesso)

        // Referências para os campos de texto e botões
        val botaoVoltar: ImageView = findViewById(R.id.botaovoltar)
        val dataNascimentoEditText: EditText = findViewById(R.id.editTextDataNascimento)
        val emailEditText: EditText = findViewById(R.id.editTextEmail)
        val confirmEmailEditText: EditText = findViewById(R.id.editTextConfirmEmail)
        val celularEditText: EditText = findViewById(R.id.editTextCelular)
        val senhaEditText: EditText = findViewById(R.id.editTextSenha)
        val confirmSenhaEditText: EditText = findViewById(R.id.editTextConfirmSenha)
        val botaoRegistrar: Button = findViewById(R.id.buttonRegistrar)

        // Configurando o botão de voltar
        botaoVoltar.setOnClickListener {
            finish()
        }

        // Adicionando a máscara de data ao campo de data de nascimento
        dataNascimentoEditText.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val mask = "##/##/####"

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) {
                    isUpdating = false
                    return
                }

                val unmasked = s.toString().replace(Regex("\\D"), "")
                val masked = StringBuilder()

                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && unmasked.length > i) {
                        masked.append(m)
                        continue
                    } else if (i >= unmasked.length) {
                        break
                    }
                    masked.append(unmasked[i])
                    i++
                }

                isUpdating = true
                dataNascimentoEditText.setText(masked.toString())
                dataNascimentoEditText.setSelection(masked.length)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Verificar se todos os campos estão preenchidos para habilitar o botão "Cadastrar"
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                botaoRegistrar.isEnabled = dataNascimentoEditText.text.isNotEmpty() &&
                        emailEditText.text.isNotEmpty() &&
                        confirmEmailEditText.text.isNotEmpty() &&
                        celularEditText.text.isNotEmpty() &&
                        senhaEditText.text.isNotEmpty() &&
                        confirmSenhaEditText.text.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        dataNascimentoEditText.addTextChangedListener(textWatcher)
        emailEditText.addTextChangedListener(textWatcher)
        confirmEmailEditText.addTextChangedListener(textWatcher)
        celularEditText.addTextChangedListener(textWatcher)
        senhaEditText.addTextChangedListener(textWatcher)
        confirmSenhaEditText.addTextChangedListener(textWatcher)

        // Ação de registrar o usuário
        botaoRegistrar.setOnClickListener {
            val email = emailEditText.text.toString()
            val confirmEmail = confirmEmailEditText.text.toString()
            val senha = senhaEditText.text.toString()
            val confirmSenha = confirmSenhaEditText.text.toString()

            // Verifica se os e-mails e senhas coincidem
            if (email == confirmEmail && senha == confirmSenha) {
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.putString("senha", senha)
                editor.apply()

                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()

                dataNascimentoEditText.text.clear()
                emailEditText.text.clear()
                confirmEmailEditText.text.clear()
                celularEditText.text.clear()
                senhaEditText.text.clear()
                confirmSenhaEditText.text.clear()

                finish()
            } else {
                Toast.makeText(this, "E-mails ou senhas não coincidem.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
