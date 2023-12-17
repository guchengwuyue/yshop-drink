export default {
    props: {
        openType: String
    },
		emits: ['getphonenumber','getuserinfo','error','opensetting','launchapp','contact','chooseavatar','addgroupapp','chooseaddress','subscribe','login','im'],
    methods: {
        onGetPhoneNumber(event) {
            this.$emit('getphonenumber', event.detail)
        },
        onGetUserInfo(event) {
            this.$emit('getuserinfo', event.detail)
        },
        onError(event) {
            this.$emit('error', event.detail)
        },
        onOpenSetting(event) {
            this.$emit('opensetting', event.detail)
        },
        onLaunchApp(event) {
            this.$emit('launchapp', event.detail)
        },
        onContact(event) {
            this.$emit('contact', event.detail)
        },
        onChooseavatar(event) {
            this.$emit('chooseavatar', event.detail)
        },
        onAgreeprivacyauthorization(event) {
            this.$emit('agreeprivacyauthorization', event.detail)
        },
        onAddgroupapp(event) {
            this.$emit('addgroupapp', event.detail)
        },
        onChooseaddress(event) {
            this.$emit('chooseaddress', event.detail)
        },
        onSubscribe(event) {
            this.$emit('subscribe', event.detail)
        },
        onLogin(event) {
            this.$emit('login', event.detail)
        },
        onIm(event) {
            this.$emit('im', event.detail)
        }
    }
}
