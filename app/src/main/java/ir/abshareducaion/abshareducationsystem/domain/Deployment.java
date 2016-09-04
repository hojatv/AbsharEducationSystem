package ir.abshareducaion.abshareducationsystem.domain;

/**
 * Created by Dell on 9/4/2016.
 */
public class Deployment {
    private String id;
    private String name;
    private String deploymentTime;
    private String category;
    private String url;
    private String tenantId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeploymentTime() {
        return deploymentTime;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }

    public String getTenantId() {
        return tenantId;
    }
}
