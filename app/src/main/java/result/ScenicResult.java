package result;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.LocationInfo;
import model.ScenicInfo;

/**
 * Created by tao on 2015/12/17.
 */
public class ScenicResult extends BaseResult {
    @JsonProperty("result")
    private ScenicInfo result;

    public ScenicInfo getResult() {
        return result;
    }

    public void setResult(ScenicInfo result) {
        this.result = result;
    }
}
