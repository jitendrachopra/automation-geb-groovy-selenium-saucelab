package common.util

class GebConfigHelper{

    def static profile = System.getProperty('profile')

    def static username = System.getProperty('automationCredentials.username')
    def static apiKey = System.getProperty('automationCredentials.apiKey')
    def static automationURL =System.getProperty('automationURL')
    def static browser =System.getProperty('geb.env')
    def static appPropertiesMap;


    public static getBaseURL(){
        appPropertiesMap = YamlReader.loadYml()
        if(GebConfigHelper.profile.equals(Env.PROD)){
            return appPropertiesMap['url']['prod']
        }else if(GebConfigHelper.profile.equals(Env.QA)){
            return  appPropertiesMap['url']['qa']
        }else{
            return appPropertiesMap['url']['prod']
        }

    }
//enviorment
    public static isQA(){
        return GebConfigHelper.profile.equals(Env.QA)
    }
    public static isProd(){
        return GebConfigHelper.profile.equals(Env.PROD)
    }
    public static isPef(){
        return GebConfigHelper.profile.equals(Env.PERF)
    }
    public static isDev(){
        return GebConfigHelper.profile.equals(Env.DEV)
    }

//browser
    public static isFireFox(){
        return GebConfigHelper.browser.equalsIgnoreCase(Browser.FIREFOX)
    }

    public static isSafari(){
        return GebConfigHelper.browser.equalsIgnoreCase(Browser.SAFARI)
    }

    public static isChrome(){
        return GebConfigHelper.browser.equalsIgnoreCase(Browser.CHROME)
    }
    public static isIE(){
        return GebConfigHelper.browser.equalsIgnoreCase(Browser.IE)
    }
}