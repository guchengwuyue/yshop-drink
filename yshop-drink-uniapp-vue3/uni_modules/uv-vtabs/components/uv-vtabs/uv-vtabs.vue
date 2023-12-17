<template>
	<view
		class="uv-vtabs"
		:style="[vtabsStyle]"
	>
		<scroll-view
			class="uv-vtabs__bar"
			ref="uv-vtabs__bar"
			:style="[getBarStyle]"
			:scroll-y="barScrollable" 
			:scroll-x="scrollX"
			:show-scrollbar="false"
			:scroll-with-animation="true"
			:scroll-top="barScrollTop"
			:scroll-into-view="barScrollToView"
		>
			<view
				:class="[
					'uv-vtabs__bar-item',
					`uv-vtabs__bar-item--${index}`,
					index == activeIndex && 'uv-vtabs__bar-item-active'
				]"
				:ref="`uv-vtabs__bar-item--${index}`"
				v-for="(item,index) in list"
				:key="index"
				:id="`bar_${index}`"
				:style="[itemStyle(index)]"
				@tap.stop="clickHandler(index)"
			>
				<view 
					class="uv-vtabs__bar-item--line"
					v-if="index == activeIndex"
					:style="[$uv.addStyle(barItemActiveLineStyle)]"
				></view>
				<text 
					:class="[
						'uv-vtabs__bar-item--value',
						index == activeIndex && 'uv-vtabs__bar-item-active--value'
					]"
					:style="[itemStyle(index),textStyle(index)]"
				>{{item[keyName]}}</text>
				<view 
					class="uv-vtabs__bar-item--badge"
					:style="[$uv.addStyle(barItemBadgeStyle)]"
					v-if="!!(item.badge && (item.badge.show || item.badge.isDot || item.badge.value))"
				>
					<uv-badge
						:show="!!(item.badge && (item.badge.show || item.badge.isDot || item.badge.value))"
						:isDot="item.badge && item.badge.isDot || propsBadge.isDot"
						:value="item.badge && item.badge.value || propsBadge.value"
						:max="item.badge && item.badge.max || propsBadge.max"
						:type="item.badge && item.badge.type || propsBadge.type"
						:showZero="item.badge && item.badge.showZero || propsBadge.showZero"
						:bgColor="item.badge && item.badge.bgColor || propsBadge.bgColor"
						:color="item.badge && item.badge.color || propsBadge.color"
						:shape="item.badge && item.badge.shape || propsBadge.shape"
						:numberType="item.badge && item.badge.numberType || propsBadge.numberType"
						:inverted="item.badge && item.badge.inverted || propsBadge.inverted"
					></uv-badge>
				</view>
			</view>
		</scroll-view>
		<scroll-view
			class="uv-vtabs__content"
			:style="[getContentStyle,$uv.addStyle(contentStyle)]"
			:scroll-y="true" 
			:scroll-x="scrollX"
			:show-scrollbar="false"
			:scroll-top="contentScrollTop"
			:scroll-into-view="contentScrollTo"
			:scroll-with-animation="true"
			@scroll="scrollHandler"
			@scrolltolower="scrolltolower"
			v-if="chain"
		>
			<slot />
		</scroll-view>
		<scroll-view 
			v-else
			class="uv-vtabs__content"
			:style="[getContentStyle,$uv.addStyle(contentStyle)]"
			:scroll-y="true" 
			:scroll-x="scrollX"
			:show-scrollbar="false"
			:scroll-top="contentScrollTop2"
			@scrolltolower="scrolltolower"
		>
			<slot />
		</scroll-view>
	</view>
</template>
<script>
	import mpMixin from '@/uni_modules/uv-ui-tools/libs/mixin/mpMixin.js'
	import mixin from '@/uni_modules/uv-ui-tools/libs/mixin/mixin.js'
	import debounce from '@/uni_modules/uv-ui-tools/libs/function/debounce.js'
	import throttle from '@/uni_modules/uv-ui-tools/libs/function/throttle.js'
	import uvBadgeProps from '@/uni_modules/uv-badge/components/uv-badge/props.js'
	import props from './props.js';
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom')
	// #endif
	/**
	 * 垂直选项卡
	 * @description 该组件兼容所有端，提供了分类展示和联动等功能
	 * @tutorial https://www.uvui.cn/components/vtabs.html
	 * @property {Array}	list  选项数组，元素为对象，如[{name:'uv-ui'}]（默认 [] ）
	 * @property {String}  keyName  从list元素对象中读取的键名（默认 name ）
	 * @property {Number}  current  当前选中项，从0开始（默认 0 ）
	 * @property {Number | String}  hdHeight  头部内容的高度，头部有内容必传，否则会有联动误差（默认 0 ）
	 * @property {Boolean}  chain  是否开启联动，开启后右边区域可以滑动查看内容（默认 true ）
	 * @property {Number|String}  height  整个列表的高度，默认auto或空则为屏幕高度（默认 auto屏幕高度 ）
	 * @property {Number|String}  barWidth  左边选项区域的宽度（默认 180rpx ）
	 * @property {Boolean}  barScrollable  左边选项区域是否允许滚动 （默认 true ）
	 * @property {String}  barBgColor  左边选项区域的背景颜色（默认$uv-bg-color）
	 * @property {Object}  barStyle  左边选项区域的自定义样式 （默认{}）
	 * @property {Object}  barItemStyle  左边选项区域每个选项的自定义样式 （默认{}）
	 * @property {Object}  barItemActiveStyle  左边选项区域选中选项的自定义样式 （默认{}）
	 * @property {Object}  barItemActiveLineStyle  左边选项区域选中选项竖线条的自定义样式 （默认{}）
	 * @property {Object}  barItemBadgeStyle  左边选项区域选中选项徽标的自定义样式，主要用于设置位置 （默认{}）
	 * @property {Object}  contentStyle  右边区域自定义样式 （默认{}）
	 * @example <uv-vtabs :list="list"><uv-vtabs-item>...</uv-vtabs-item></uv-vtabs>
	 */
	export default {
		name: 'uv-vtabs',
		mixins: [mpMixin, mixin, props],
		created() {
			this.children = []
		},
		data() {
			return {
				activeIndex: 0,
				// 微信小程序下，scroll-view的scroll-into-view属性无法对slot中的内容的id生效，只能通过设置scrollTop的形式去移动滚动条
				contentScrollTop: 0,
				contentScrollTop2: 0,//针对非联动
				contentScrollTo: '',
				scrolling: false,
				barScrolling: false,
				touching: false,
				hasHeight: 0,
				scrollViewHeight: 0,
				barScrollTop: 0,
				barScrollToView: ''
			}
		},
		computed: {
			scrollX(){
				// #ifdef APP-NVUE
				return true;
				// #endif
				return false;
			},
			vtabsStyle() {
				const style = {};
				style.height = this.getHeight();
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.customStyle));
			},
			getBarStyle() {
				const style = {};
				style.width = this.$uv.getPx(this.barWidth, true);
				style.background = this.barBgColor;
				style.height = this.getHeight();
				return this.$uv.deepMerge(style, this.$uv.addStyle(this.barStyle));
			},
			itemStyle(){
				return index =>{
					const style = {};
					let barItemInitStyle = this.barItemStyle;
					// 避免在nvue模式下，切换时候上一个选中颜色不变
					if(this.barItemStyle && !this.barItemStyle?.background) {
						barItemInitStyle.background = 'transparent';
					}
					// 是否激活的样式
					const customeStyle = index === this.activeIndex ? this.$uv.addStyle(this.barItemActiveStyle) : this.$uv.addStyle(barItemInitStyle);
					if (this.list[index].disabled) {
						style.color = '#c8c9cc'
					}
					return this.$uv.deepMerge(customeStyle, style);
				} 
			},
			// nvue设置字体样式必须要text标签上进行
			textStyle(){
				return index=>{
					const style = {};
					style.width = this.$uv.getPx(this.barWidth, true);
					return style;
				}
			},
			getContentStyle() {
				const style = {};
				style.height = this.getHeight();
				return style;
			},
			propsBadge() {
				return uvBadgeProps
			}
		},
		watch: {
			current: {
				deep: true,
				immediate: true,
				handler(newVal){
					this.init(newVal?newVal:0);
				}
			},
			list(newVal) {
				if (newVal.length) {
					this.$uv.sleep(30).then(res => {
						this.resize();
					})
				}
			},
			activeIndex(newVal){
				if(!this.chain) {// 解决：非联动，内容过多的情况，滚动一段距离，再切换未滚动到顶部的BUG
					this.contentScrollTop2 = 0 - Math.random() * 4 - 4;
				}
				this.$emit('change',newVal);
			}
		},
		methods: {
			init(index){
				let num = 0;
				this.timer2 && clearInterval(this.timer2);
				this.timer2 = setInterval(async ()=>{
					num++;
					if(num>50) clearInterval(this.timer2);
					if(this.children.length) {
						clearInterval(this.timer2);
						await this.$uv.sleep(300);
						this.clickHandler(index);
					}
				},100)
			},
			// 内容滚动到底部触发
			scrolltolower(){
				this.$emit('scrolltolower',this.activeIndex);
			},
			async resize() {
				// 如果list数组长度为0就不处理 || 选中目标未变则不处理
				if (this.list.length == 0 || !this.barScrollable) return;
				// 避免滑太快，修复位置
				Promise.all([this.getTabsRect(), this.getAllItemRect()]).then(([tabsRect, itemRect = []]) => {
					this.tabsRect = tabsRect;
					this.scrollViewHeight = 0
					itemRect.map((item, index) => {
						this.scrollViewHeight += item.height;
						this.list[index].rect = item;
					})
					this.setBarScrollTop();
				})
			},
			// 设置左边菜单滚动条的位置，目标：将当前的选项移到中间位置
			setBarScrollTop() {
				const tabRect = this.list[this.activeIndex];
				const offsetTop = this.list
					.slice(0, this.activeIndex)
					.reduce((total, item) => {
						return total + item.rect.height;
					}, 0);
				const scrollViewHeight = this.$uv.getPx(this.getHeight());
				let barScrollTop = tabRect.rect.height / 2 + offsetTop - scrollViewHeight / 2;
				// 先给一点随机值，避免出现不能滚动的BUG
				barScrollTop = Math.min(barScrollTop, this.scrollViewHeight - this.tabsRect.height);
				this.barScrollTop = Math.max(0, barScrollTop);
				// 已经不能滚动的时候，就使用scroll-into-view的方式进行定位，避免失效
				if(barScrollTop>=(this.scrollViewHeight - this.tabsRect.height)) {
					this.timer && clearTimeout(this.timer);
					this.timer = setTimeout(()=>{
						this.barScrollToView = `bar_${this.activeIndex}`;
					},400)
				}
			},
			// 左边菜单点击
			async clickHandler(currentIndex) {
				if (currentIndex == this.activeIndex) return;
				this.touching = true;
				this.activeIndex = currentIndex;
				if(this.chain) {
					// 给一点随机值，避免出现不能滚动的BUG。微信端必须用此方法
					this.contentScrollTop = this.children[currentIndex].top - this.$uv.getPx(this.hdHeight) - Math.random() * 4 - 4;
					// #ifndef MP-WEIXIN
					this.contentScrollTo = `content_${currentIndex}`;
					// #endif
				}
				this.timer && clearTimeout(this.timer);
				throttle(()=>{
					this.resize();
				},300,false)
				debounce(() => {
					this.touching = false;
				}, 900)
			},
			// 内容滚动
			scrollHandler(e) {
				if (this.touching || this.scrolling) return;
				// 每过一定时间取样一次，减少资源损耗以及可能带来的卡顿
				this.scrolling = true;
				this.$uv.sleep(80).then(() => {
					this.scrolling = false;
				})
				const scrollTop = e.detail.scrollTop;
				let children = this.children;
				const len = children.length;
				let top = 0;
				let activeIndex = 0;
				children = this.children.map((item, index) => {
					if (item.height > 0) this.hasHeight = item.height;
					item.height = item.height > 0 ? item.height : this.hasHeight;
					const child = {
						height: item.height,
						top
					}
					// 进行累加，给下一个item提供计算依据
					top += item.height;
					return child;
				})
				for (let i = 0; i < len; i++) {
					const item = children[i];
					const nextItem = children[i + 1];
					// 如果滚动条高度小于第一个item的top值，此时无需设置任意字母为高亮
					if (scrollTop <= children[0].top) {
						activeIndex = 0;
						break
					} else if (!nextItem) {
						// 当不存在下一个item时，意味着历遍到了最后一个
						activeIndex = len - 1;
						break
					} else if (scrollTop > item.top && scrollTop < nextItem.top) {
						activeIndex = i;
						break
					}
				}
				this.activeIndex = activeIndex;
				// 当前选中项索引必然来源于前后两个索引，满足才执行，避免闪烁的bug
					this.timer4 && clearTimeout(this.timer4);
				this.timer4 = setTimeout(()=>{
					this.resize();
				},100)
			},
			// 设置高度
			getHeight() {
				let height = 0;
				const isEmpty = this.$uv.test.empty(this.height);
				if (isEmpty || this.height=='auto') height = this.$uv.addUnit(this.$uv.sys().windowHeight);
				else height = this.$uv.getPx(this.height, true);
				return height;
			},
			// 获取导航菜单的尺寸
			getTabsRect() {
				return new Promise(resolve => {
					this.queryRect('uv-vtabs__bar').then(size => resolve(size))
				})
			},
			// 获取所有标签的尺寸
			getAllItemRect() {
				return new Promise(resolve => {
					const promiseAllArr = this.list.map((item, index) => this.queryRect(
						`uv-vtabs__bar-item--${index}`, true))
					Promise.all(promiseAllArr).then(sizes => resolve(sizes))
				})
			},
			// 获取各个标签的尺寸
			queryRect(el, item) {
				// #ifndef APP-NVUE
				// $uvGetRect为uv-ui自带的节点查询简化方法，详见文档介绍：https://www.uvui.cn/js/getRect.html
				// 组件内部一般用this.$uvGetRect，对外的为getRect，二者功能一致，名称不同
				return new Promise(resolve => {
					this.$uvGetRect(`.${el}`).then(size => {
						resolve(size)
					})
				})
				// #endif
				// #ifdef APP-NVUE
				// nvue下，使用dom模块查询元素高度
				// 返回一个promise，让调用此方法的主体能使用then回调
				return new Promise(resolve => {
					dom.getComponentRect(item ? this.$refs[el][0] : this.$refs[el], res => {
						resolve(res.size)
					})
				})
				// #endif
			}
		}
	}
</script>
<style scoped lang="scss">
	@import '@/uni_modules/uv-ui-tools/libs/css/components.scss';
	@import '@/uni_modules/uv-ui-tools/libs/css/color.scss';
	.uv-vtabs {
		@include flex;
		&__bar {
			background: $uv-bg-color;
			&-item {
				position: relative;
				@include flex;
				align-items: center;
				justify-content: center;
				padding: 35rpx 12rpx 35rpx 20rpx;
				&--value {
					/* #ifdef APP-NVUE */
					padding: 0 12rpx;
					/* #endif */
					font-size: 14px;
					color: $uv-content-color;
				}
				&-active {
					background: #fff;
					&--value {
						color: $uv-primary;
					}
				}
				&--line {
					position: absolute;
					width: 2px;
					left: 0;
					top: 0;
					bottom: 0;
					z-index: 1;
					background-color: $uv-primary;
				}
				&--badge {
					position: absolute;
					top: 4px;
					right: 10px;
					z-index: 1;
				}
			}
		}
		&__content {
			flex: 1;
			background: #fff;
		}
	}
</style>