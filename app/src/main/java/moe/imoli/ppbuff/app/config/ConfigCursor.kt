package moe.imoli.ppbuff.app.config

import android.content.ContentResolver
import android.database.CharArrayBuffer
import android.database.ContentObserver
import android.database.Cursor
import android.database.DataSetObserver
import android.net.Uri
import android.os.Bundle
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge
import moe.imoli.ppbuff.app.data.ValidApps

class ConfigCursor(val prefs: YukiHookPrefsBridge) : Cursor {
    override fun close() {

    }

    override fun copyStringToBuffer(columnIndex: Int, buffer: CharArrayBuffer?) {

    }

    override fun deactivate() {

    }

    override fun getBlob(columnIndex: Int): ByteArray? {
        return null
    }

    override fun getColumnCount(): Int {
        return 1
    }

    override fun getColumnIndex(columnName: String?): Int {
        return 0
    }

    override fun getColumnIndexOrThrow(columnName: String?): Int {
        return 0
    }

    override fun getColumnName(columnIndex: Int): String? {
        return ""
    }

    override fun getColumnNames(): Array<out String?>? {
        return arrayOf()
    }

    override fun getCount(): Int {
        return 0
    }

    override fun getDouble(columnIndex: Int): Double {
        return 0.toDouble()
    }

    override fun getExtras(): Bundle? {
        return null
    }

    override fun getFloat(columnIndex: Int): Float {
        return 0f
    }

    override fun getInt(columnIndex: Int): Int {
        return 0
    }

    override fun getLong(columnIndex: Int): Long {
        return 0
    }

    override fun getNotificationUri(): Uri? {
        return null
    }

    override fun getPosition(): Int {
        return 0
    }

    override fun getShort(columnIndex: Int): Short {
        return 0
    }

    override fun getString(columnIndex: Int): String? {
        return null
    }

    override fun getType(columnIndex: Int): Int {
        return Cursor.FIELD_TYPE_NULL
    }

    override fun getWantsAllOnMoveCalls(): Boolean {
        return false
    }

    override fun isAfterLast(): Boolean {
        return false
    }

    override fun isBeforeFirst(): Boolean {
        return false
    }

    override fun isClosed(): Boolean {
        return false
    }

    override fun isFirst(): Boolean {
        return false
    }

    override fun isLast(): Boolean {
        return false
    }

    override fun isNull(columnIndex: Int): Boolean {
        return false
    }

    override fun move(offset: Int): Boolean {
        return false
    }

    override fun moveToFirst(): Boolean {
        return false
    }

    override fun moveToLast(): Boolean {
        return false
    }

    override fun moveToNext(): Boolean {
        return false
    }

    override fun moveToPosition(position: Int): Boolean {
        return false
    }

    override fun moveToPrevious(): Boolean {
        return false
    }

    override fun registerContentObserver(observer: ContentObserver?) {

    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {

    }

    override fun requery(): Boolean {
        return false
    }

    override fun respond(extras: Bundle?): Bundle? {
        if (extras != null) {
            val packageName = extras.getString("packageName")
            YLog.debug("try find config for $packageName")
            val data = ValidApps.apps.find { it.packageName == packageName }
            if (data != null) {
                val bundle = Bundle()
                data.provideSettings(bundle)
                return bundle
            }
        }
        return null
    }

    override fun setExtras(extras: Bundle?) {

    }

    override fun setNotificationUri(cr: ContentResolver?, uri: Uri?) {

    }

    override fun unregisterContentObserver(observer: ContentObserver?) {

    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {

    }
}