//This code enables the tracking of a Sync Job
import com.collibra.dgc.core.api.dto.job.FindJobsRequest
import com.collibra.dgc.core.api.model.job.JobState

//Id of the Sync Job:
def syncName = "Synchronization of batch for id: f14cfeb2-f1e1-402b-956e-07a7c0e46db4"

//Begin by finding the UUID of any relevant Sync Jobs:
def jobsOfInterest = jobApi.findJobs(FindJobsRequest.builder()
                                            .types(["IMPORT"])
                                            .states([JobState.COMPLETED])
                                            .name(syncName)
                                            .build()).getResults()

//For every job of interest log the job message and the import summary of the job
jobsOfInterest.each{
    loggerApi.info(it.getMessage())
    loggerApi.info("${importerApi.getImportJobSummary(it.getId())}")  
}
