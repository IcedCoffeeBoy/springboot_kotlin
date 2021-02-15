package com.spring.fileupload.filecontent.service

import com.spring.fileupload.common.exception.BusinessException
import com.spring.fileupload.common.utils.RandomGenerator
import com.spring.fileupload.filecontent.database.FileRecord
import com.spring.fileupload.filecontent.database.FileRecordRepository
import com.spring.fileupload.filecontent.model.FileStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional

/**
 * Handles all incoming file
 * 1) File information are recorded
 * 2) Validate the incoming file
 */
@Service
@Transactional
class FileRecordService(
        private val fileRecordRepository: FileRecordRepository
) {
    fun validateAndSave(file: MultipartFile): FileRecord {
        val sha256 = FileContentUtils.getSha256(file)
        notEmpty(file)
        checkForExisting(sha256)
        val record = FileRecord(
                id = null,
                name = file.originalFilename,
                sha256 = sha256,
                dataId = RandomGenerator.generate(),
                fileStatus = FileStatus.PROCESSING
        )
        return fileRecordRepository.save(record)
    }

    fun findByDataId(dataId: String): FileRecord {
        return fileRecordRepository.findByDataId(dataId).get();
    }

    fun update(id: Long?, status: FileStatus) {
        id?.let {
            val record = fileRecordRepository.getOne(it)
            record.fileStatus = status
            fileRecordRepository.save(record)
        }
    }

    /*-------------------------------- AUXILIARY FUNCTIONS -------------------------------- */
    private fun notEmpty(file: MultipartFile) {
        if (file.isEmpty) {
            throw BusinessException("File is empty!")
        }
    }

    private fun checkForExisting(sha256: String) {
        val record = fileRecordRepository.findBySha256(sha256)
        if (record.isPresent) {
            if (record.get().fileStatus != FileStatus.ERROR) {
                throw BusinessException("The file have been uploaded before. Please check your file")
            } else {
                fileRecordRepository.delete(record.get())
            }
        }
    }
}