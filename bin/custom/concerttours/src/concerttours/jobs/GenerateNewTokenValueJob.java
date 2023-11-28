package concerttours.jobs;

import concerttours.daos.TokenDao;
import concerttours.model.TokenModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Random;

public class GenerateNewTokenValueJob extends AbstractJobPerformable<CronJobModel> {
    private final TokenDao tokenDao;
    private final ModelService modelService;

    public GenerateNewTokenValueJob(TokenDao tokenDao, ModelService modelService) {
        this.tokenDao = tokenDao;
        this.modelService = modelService;
    }

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        TokenModel tokenModel = tokenDao.get();
        tokenModel.setToken(generateValue());
        modelService.save(tokenModel);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
    
    private String generateValue() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
