<template>
	<view class="uv-drop-down-item" @click="clickHandler">
		<uv-text :text="label" :size="getTextStyle.size" :color="getTextStyle.color" lines="1" :custom-style="{marginRight: '10rpx',maxWidth:'200rpx'}"></uv-text>
		<uv-icon :name="getDownIcon.name" :size="getDownIcon.size" :color="getDownIcon.color" v-if="[1,'1'].indexOf(type)==-1"></uv-icon>
	</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js';
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js';
	/**
	 * DropDown 下拉框
	 * @description 下拉筛选
	 * @tutorial https://ext.dcloud.net.cn/plugin?name=uv-drop-down
	 * @property {String | Number} name 字段标识
	 * @property {String | Number} type 类型 1 没有筛选项，直接进行选中和不选中  2 有多个选项
	 * @property {String | Number} label 筛选项的文本
	 * @property {Boolean} isDrop 该项是否打开
	 */
	export default {
		name: 'uv-drop-down-item',
		mixins: [mpMixin, mixin],
		emits: ['click'],
		props: {
			name: {
				type: [String, Number],
				default: ''
			},
			// 类型 1 没有筛选项，直接进行选中和不选中  2 有多个选项
			type: {
				type: [String, Number],
				default: '2'
			},
			// 筛选的文本
			label: {
				type: [String],
				default: ''
			},
			// 筛选值
			value: {
				type: [String, Number, null],
				default: ''
			},
			// 是否下拉菜单打开
			isDrop: {
				type: Boolean,
				default: false
			}
		},
		data() {
			return {
				parentData: {
					defaultValue: [0, '0', 'all'],
					textSize: '30rpx',
					textColor: '#333',
					textActiveSize: '30rpx',
					textActiveColor: '#3c9cff',
					extraIcon: {},
					extraActiveIcon: {},
					sign: '',
					clickHandler: Function
				},
				active: false,
				isDroped: false,
				elId: ''
			}
		},
		watch: {
			isDrop: {
				handler(newVal) {
					this.isDroped = newVal;
				},
				immediate: true
			},
			value: {
				handler(newVal) {
					this.active = this.parentData.defaultValue.indexOf(newVal) == -1;
				},
				immediate: true
			}
		},
		computed: {
			getDownIcon() {
				const style = {
					size: '30rpx',
					color: '#333',
					...this.parentData.extraIcon
				}
				if (this.active || this.isDroped) {
					style.color = this.parentData.extraActiveIcon?.color ? this.parentData.extraActiveIcon?.color : '#3c9cff';
					style.size = this.parentData.extraActiveIcon?.size ? this.parentData.extraActiveIcon?.size : '30rpx';
				}
				if (this.isDroped) {
					style.name = this.parentData.extraActiveIcon?.name;
				}
				return style;
			},
			getTextStyle() {
				const style = {
					size: this.parentData.textSize,
					color: this.parentData.textColor
				};
				if (this.active || this.isDroped) {
					style.size = this.parentData.textActiveSize;
					style.color = this.parentData.textActiveColor;
				}
				return style;
			}
		},
		created() {
			this.init();
		},
		methods: {
			init() {
				this.elId = this.$uv.guid();
				this.getParentData('uv-drop-down');
				if (!this.parent) {
					this.$uv.error('uv-drop-down必须搭配uv-drop-down-item组件使用');
				}
				uni.$on('HANDLE_DROPDOWN_ONE', id => {
					if (this.isDroped && this.elId != id) {
						this.isDroped = false;
					}
				})
				uni.$on(`${this.parentData.sign}_CLOSEPOPUP`, async () => {
					if (this.isDroped) {
						this.isDroped = false;
					}
				})
			},
			async clickHandler() {
				let data = {};
				uni.$emit('HANDLE_DROPDOWN_ONE', this.elId);
				switch (+this.type) {
					case 1:
						this.active = !this.active;
						data = {
							name: this.name,
							active: this.active,
							type: this.type
						};
						break;
					case 2:
						this.isDroped = !this.isDroped;
						data = {
							name: this.name,
							active: this.isDroped,
							type: this.type
						};
						break;
				}
				this.parentData.clickHandler(data);
				this.$emit('click', data);
				uni.$emit(`${this.parentData.sign}_CLICKMENU`, {
					show: +this.type > 1 && this.isDroped
				});
			}
		}
	}
</script>
<style scoped lang="scss">
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	.uv-drop-down-item {
		@include flex;
		align-items: center;
		padding: 20rpx;
	}
</style>