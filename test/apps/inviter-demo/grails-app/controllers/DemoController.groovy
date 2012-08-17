
class DemoController {
    
    def save() {
        log.info('demo save')
        def txt = ''
        params.each { k, v ->
            txt += "${k}   : ${v}\n"
        }
        [paramsAsText: txt]
    }
}
