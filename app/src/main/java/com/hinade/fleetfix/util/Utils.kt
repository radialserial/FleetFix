package com.hinade.fleetfix.util

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Classe contendo utilidades usandas pelo aplicativo.
 */
class Utils {

    companion object {
        private const val CRIPT_ITERACOES = 4096
        private const val CRIPT_TAMANHO_CHAVE = 512
        private const val CRIPT_ALGORITMO = "PBKDF2WithHmacSHA512"

        /**
         * Criptografa senhas usando um algoritmo seguro.
         *
         * @param senha a senha a ser criptografada.
         * @param salt o "sal" aleatório a ser usado para criptografar a senha.
         */
        @OptIn(ExperimentalStdlibApi::class)
        fun getSenhaCriptografada(
            senha: String,
            salt: String,
        ) = SecretKeyFactory.getInstance(CRIPT_ALGORITMO)
            .generateSecret(
                PBEKeySpec(
                    senha.toCharArray(),
                    salt.toByteArray(),
                    CRIPT_ITERACOES,
                    CRIPT_TAMANHO_CHAVE
                )
            ).encoded.toHexString()
    }

}