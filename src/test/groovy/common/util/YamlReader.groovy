package common.util
import org.yaml.snakeyaml.Yaml


class YamlReader {


    public static loadYml() {
        def yaml = new Yaml()
        def doc = YamlReader.class.getClassLoader().getResourceAsStream('application.yml').text
        def map = (Map) yaml.load(doc)

        return map
    }


}
