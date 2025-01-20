import { mount } from '@vue/test-utils'
import App from '@/App.vue'

// Mock fetch to avoid actual API calls
global.fetch = jest.fn(() =>
    Promise.resolve({
        json: () =>
            Promise.resolve([
                { speed: 1, direction: 'clockwise' } // Mocked API response
            ])
    })
)

describe('App.vue', () => {
    beforeEach(() => {
        jest.clearAllMocks()
    })

    it('renders initial UI correctly', async () => {
        const wrapper = mount(App)

        // Wait for mounted lifecycle method to complete
        await wrapper.vm.$nextTick()

        expect(wrapper.find('.ceiling-fan-image').exists()).toBe(true)
        expect(wrapper.text()).toContain('Direction: clockwise')
        expect(wrapper.text()).toContain('Speed: 0')
    })

    it('fetches fan data on mount and updates UI', async () => {
        const wrapper = mount(App)

        // Wait for mounted lifecycle method to complete
        await wrapper.vm.$nextTick()

        expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/fans')
        expect(wrapper.vm.speed).toBe(0)
        expect(wrapper.vm.direction).toBe('clockwise')
        expect(wrapper.text()).toContain('Direction: clockwise')
        expect(wrapper.text()).toContain('Speed: 0')
    })

    it('increases speed when Speed button is clicked', async () => {
        const wrapper = mount(App)

        // Mock fetch response for update
        fetch.mockImplementation(() =>
            Promise.resolve({
                json: () =>
                    Promise.resolve({ speed: 1, direction: 'clockwise' }) // Mock response
            })
        )

        await wrapper.vm.$nextTick() // Wait for initial fetch

        // Click Speed button
        await wrapper.find('button').trigger('click')

        expect(wrapper.vm.speed).toBe(1) // Speed should increase
        expect(wrapper.text()).toContain('Speed: 1')
    })

    it('toggles direction when Direction button is clicked', async () => {
        const wrapper = mount(App)

        // Mock fetch response for update
        fetch.mockImplementation(() =>
            Promise.resolve({
                json: () =>
                    Promise.resolve({ speed: 0, direction: 'counter-clockwise' }) // Mock response
            })
        )

        await wrapper.vm.$nextTick() // Wait for initial fetch

        // Click Direction button
        await wrapper.findAll('button').at(1).trigger('click') // Second button

        expect(wrapper.vm.direction).toBe('counter-clockwise') // Direction should toggle
        expect(wrapper.text()).toContain('Direction: counter-clockwise')
    })

    it('resets speed to 0 when it exceeds the maximum value', async () => {
        const wrapper = mount(App)

        // Mock fetch response for update
        fetch.mockImplementation(() =>
            Promise.resolve({
                json: () =>
                    Promise.resolve({ speed: 0, direction: 'clockwise' }) // Mock response
            })
        )

        await wrapper.vm.$nextTick() // Wait for initial fetch

        // Simulate speed reaching maximum and reset
        wrapper.setData({ speed: 3 }) // Set speed to maximum
        await wrapper.find('button').trigger('click') // Click Speed button

        expect(wrapper.vm.speed).toBe(0) // Speed should reset to 0
        expect(wrapper.text()).toContain('Speed: 0')
    })
})
