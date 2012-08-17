modules = {
    'inviter' {
        dependsOn 'jquery'
        resource url:  [dir: 'js', file: 'inviter.js', plugin: 'inviter']
        resource url:  [dir: 'css', file: 'inviter.css', plugin: 'inviter']
    }
}